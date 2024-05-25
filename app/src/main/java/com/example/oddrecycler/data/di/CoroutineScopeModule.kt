package com.example.oddrecycler.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

@Module
@InstallIn(SingletonComponent::class)
class CoroutineScopeModule {
    @Provides
    fun provideCoroutineScope(context: CoroutineContext): CoroutineScope = CoroutineScope(context)

    @Provides
    fun coroutineContext(): CoroutineContext = SupervisorJob()
}