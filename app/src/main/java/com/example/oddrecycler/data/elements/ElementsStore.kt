package com.example.oddrecycler.data.elements

import android.util.Log
import com.example.oddrecycler.data.entity.ElementDataObject
import com.example.oddrecycler.util.Dispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ElementsStore @Inject constructor(
    externalScope: CoroutineScope,
    dispatcher: Dispatcher
) {
    private val data = mutableListOf<ElementDataObject>()
    private val mutex = Mutex()

    private val _elements = MutableSharedFlow<List<ElementDataObject>>()

    val elements: SharedFlow<List<ElementDataObject>>
        get() = _elements

    init {
        externalScope.launch(dispatcher.default) {
            startGenerator()
        }
    }

    private suspend fun startGenerator() {
        while (true) {
            mutex.withLock {
                _elements.emit(data.apply {
                    add(
                        (0..size).random(),
                        element = ElementStorage.get() ?: ElementGenerator.next()
                    )
                })
            }
            delay(5000)
        }
    }

    suspend fun removeAt(id: Int) {
        mutex.withLock {
            data.apply {
                indexOfFirst { element ->
                    element.id == id
                }.let { index ->
                    apply {
                        ElementStorage.put(elementAt(index))
                        removeAt(index)
                        _elements.emit(this)
                    }
                }
            }
        }
    }
}