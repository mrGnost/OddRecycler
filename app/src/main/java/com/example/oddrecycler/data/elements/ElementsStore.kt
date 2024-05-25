package com.example.oddrecycler.data.elements

import com.example.oddrecycler.data.entity.Element
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class ElementsStore @Inject constructor(externalScope: CoroutineScope) {
    private val data = mutableListOf<Element>()

    private val _elements = flow<List<Element>> {
        while (true) {
            emit(data.apply {
                add((0..size).random(), ElementGenerator.next())
            })
            delay(5000)
        }
    }.shareIn(externalScope, replay = 1, started = SharingStarted.WhileSubscribed())
    val elements: SharedFlow<List<Element>>
        get() = _elements

    init {
        externalScope.launch(Dispatchers.Default) {
            startGenerator()
        }
    }

    suspend fun startGenerator() {

    }

    fun removeAt(id: Int) {
        data.apply {
            indexOfFirst { element ->
                element.id == id
            }.let { index ->
                apply {
                    removeAt(index)
                }
            }
        }
    }
}