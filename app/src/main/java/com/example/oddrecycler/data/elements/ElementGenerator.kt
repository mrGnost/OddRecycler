package com.example.oddrecycler.data.elements

import com.example.oddrecycler.data.entity.Element

object ElementGenerator {
    private var counter = 0

    fun next() = Element(counter++)
}