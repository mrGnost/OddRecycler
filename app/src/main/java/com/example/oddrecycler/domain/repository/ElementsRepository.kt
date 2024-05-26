package com.example.oddrecycler.domain.repository

import com.example.oddrecycler.domain.entity.Element
import kotlinx.coroutines.flow.Flow

interface ElementsRepository {
    fun getElements(): Flow<List<Element>>

    suspend fun removeElement(id: Int)
}