package com.example.mvi.livedata

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner

class CustomLiveData<T> {

    private val hashMapOfObservers: HashMap<(T?) -> Unit, LifeCycleObserverWrapper> = HashMap()
    private var dataHolder: T? = null

    fun postValue(value: T) {
        dataHolder = value
        hashMapOfObservers.forEach { it ->
            if (it.value.lifecycleOwner.lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED))
                it.key.invoke(value)
        }
    }


    fun addObserver(observer: (T?) -> Unit, lifecycleOwner: LifecycleOwner) {
        LifeCycleObserverWrapper(lifecycleOwner, observer).apply {
            lifecycleOwner.lifecycle.addObserver(this)
            hashMapOfObservers[observer] = this
        }

    }

    fun removeObserver(observer: (T?) -> Unit) {
        hashMapOfObservers[observer]?.run {
            lifecycleOwner.lifecycle.removeObserver(this)
        }

    }


    fun updateValue(observer: (T?) -> Unit) {
        observer.invoke(dataHolder)
    }


    inner class LifeCycleObserverWrapper(
        val lifecycleOwner: LifecycleOwner,
        private val observer: (T) -> Unit
    ) :
        DefaultLifecycleObserver {

        override fun onCreate(owner: LifecycleOwner) {
            super.onCreate(owner)
            updateValue { observer }
        }

        override fun onResume(owner: LifecycleOwner) {
            super.onResume(owner)
            updateValue { observer }
        }

        override fun onDestroy(owner: LifecycleOwner) {
            super.onDestroy(owner)

            removeObserver { observer }
        }

    }


}