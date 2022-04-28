package com.example.mvi

sealed class MainIntent {
    object PlayVideo: MainIntent()
    object PauseVideo: MainIntent()
}
