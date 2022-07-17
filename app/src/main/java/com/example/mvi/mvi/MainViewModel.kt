package com.example.mvi.mvi

import androidx.lifecycle.ViewModel
import com.example.mvi.mvi.MainIntent
import com.example.mvi.mvi.MainState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

class MainViewModel : BaseModel<MainState, MainSideEffect>(){

    fun handleUserActions(action: MainIntent) {
        when (action) {
            MainIntent.PauseVideo -> intent {
                postSideEffect(MainSideEffect.Toast("Paused"))
                reduce {
                    state.apply {
                        copy(
                            state = MainState.Playing
                        )
                    }
                }
            }
            MainIntent.PlayVideo -> intent {
                postSideEffect(MainSideEffect.Toast("Playing"))
            }
        }
    }
}