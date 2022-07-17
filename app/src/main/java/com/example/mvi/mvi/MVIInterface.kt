package com.example.mvi.mvi

interface MVIInterface<T, V> {
    fun handleState(state: T)
    fun handleSideEffect(effect: V)
}

