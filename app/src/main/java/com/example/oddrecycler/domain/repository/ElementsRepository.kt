package com.example.oddrecycler.domain.repository

import com.example.oddrecycler.data.entity.Element
import kotlinx.coroutines.flow.SharedFlow

interface ElementsRepository {
    fun getElements(): SharedFlow<List<Element>>

    suspend fun removeElement(id: Int)
}