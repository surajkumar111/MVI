package com.example.mvi.mvi

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container

abstract class BaseModel<STATE : Any, SIDE_EFFECT : Any> : ContainerHost<STATE, SIDE_EFFECT>,
    ViewModel() {

    object DefaultState

    // Include `orbit-viewmodel` for the factory function
    @Suppress("UNCHECKED_CAST")
    override val container = container<STATE, SIDE_EFFECT>(
        initialState = DefaultState as STATE
    )


    fun sideFlow() = container.sideEffectFlow
    fun stateFlow() = container.stateFlow


}