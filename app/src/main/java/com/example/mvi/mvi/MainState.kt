package com.example.mvi.mvi

sealed class MainState {
    object Initial : MainState()
    object Loading : MainState()
    object Playing : MainState()
    object Paused : MainState()
    object VideoLoaded : MainState()
}

sealed class MainSideEffect {
    data class Toast(val text: String) : MainSideEffect()
}