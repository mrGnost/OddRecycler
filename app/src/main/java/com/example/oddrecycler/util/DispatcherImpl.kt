package com.example.oddrecycler.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DispatcherImpl @Inject constructor() : Dispatcher {
    override val io: CoroutineDispatcher
        get() = Dispatchers.IO
    override val default: CoroutineDispatcher
        get() = Dispatchers.Default
    override val ui: CoroutineDispatcher
        get() = Dispatchers.Main
}