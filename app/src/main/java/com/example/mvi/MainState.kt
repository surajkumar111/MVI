package com.example.mvi

sealed class MainState{
    object Initial: MainState()
    object Loading: MainState()
    object Playing: MainState()
    object Paused: MainState()
    object VideoLoaded: MainState()
}
