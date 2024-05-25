package com.example.oddrecycler.util

import kotlinx.coroutines.CoroutineDispatcher

interface Dispatcher {
    val io: CoroutineDispatcher
    val default: CoroutineDispatcher
    val ui: CoroutineDispatcher
}