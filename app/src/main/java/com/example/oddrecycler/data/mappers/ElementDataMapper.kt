package com.example.oddrecycler.data.mappers

import com.example.oddrecycler.data.entity.ElementDataObject
import com.example.oddrecycler.domain.entity.Element
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.map

fun ElementDataObject.toElement() = Element(id)

fun SharedFlow<List<ElementDataObject>>.toElementsFlow() = map { flow ->
    flow.map { it.toElement() }
}