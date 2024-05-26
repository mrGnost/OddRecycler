package com.example.oddrecycler.data.elements

import com.example.oddrecycler.data.entity.ElementDataObject

object ElementStorage {
    private val storage = mutableSetOf<ElementDataObject>()

    fun put(element: ElementDataObject) = storage.add(element)

    fun get(): ElementDataObject? {
        return storage.run {
            randomOrNull().also {
                remove(it)
            }
        }
    }
}