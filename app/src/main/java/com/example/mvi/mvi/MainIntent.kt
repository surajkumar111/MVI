package com.example.mvi.mvi

sealed class MainIntent {
    object PlayVideo: MainIntent()
    object PauseVideo: MainIntent()
}
