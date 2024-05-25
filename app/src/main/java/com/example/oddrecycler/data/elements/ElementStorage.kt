package com.example.oddrecycler.data.elements

import com.example.oddrecycler.data.entity.Element

object ElementStorage {
    private val storage = mutableSetOf<Element>()

    fun put(element: Element) = storage.add(element)

    fun get(): Element? {
        return storage.run {
            randomOrNull().also {
                remove(it)
            }
        }
    }
}