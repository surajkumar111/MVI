package com.example.mvi.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import javax.inject.Inject

@Module
@InstallIn(ActivityComponent::class)
class NetworkModule {
    @Provides
    @ActivityScoped
    fun getString() = "Suyraj"

}