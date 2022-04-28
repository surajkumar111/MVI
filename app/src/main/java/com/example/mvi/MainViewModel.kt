package com.example.mvi

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val _mainState = MutableStateFlow<MainState>(MainState.Initial)
    val mainState: StateFlow<MainState> get() = _mainState

    val mainIntent = Channel<MainIntent>(Channel.UNLIMITED)

    fun setupIntentObserver() {
        CoroutineScope(Dispatchers.IO).launch {
            mainIntent.consumeAsFlow().collect {
                when (it) {
                    MainIntent.PauseVideo -> _mainState.value = MainState.Paused
                    MainIntent.PlayVideo -> _mainState.value = MainState.Playing
                }
            }
        }

    }
}