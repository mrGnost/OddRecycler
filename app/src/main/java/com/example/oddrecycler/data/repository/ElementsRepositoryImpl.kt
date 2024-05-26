package com.example.oddrecycler.data.repository

import com.example.oddrecycler.data.elements.ElementsStore
import com.example.oddrecycler.data.mappers.toElementsFlow
import com.example.oddrecycler.domain.repository.ElementsRepository
import com.example.oddrecycler.util.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ElementsRepositoryImpl @Inject constructor(
    private val dispatcher: Dispatcher,
    private val store: ElementsStore
) : ElementsRepository {
    override fun getElements() = store.elements.toElementsFlow()

    override suspend fun removeElement(id: Int) {
        withContext(dispatcher.io) {
            store.removeAt(id)
        }
    }
}