package com.example.oddrecycler.data.elements

import com.example.oddrecycler.data.entity.ElementDataObject

object ElementGenerator {
    private var counter = 0

    fun next() = ElementDataObject(counter++)
}