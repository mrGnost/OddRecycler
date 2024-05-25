package com.example.oddrecycler.data.repository

import com.example.oddrecycler.data.elements.ElementsStore
import com.example.oddrecycler.domain.repository.ElementsRepository
import com.example.oddrecycler.util.Dispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ElementsRepositoryImpl @Inject constructor(
    val dispatcher: Dispatcher,
    val store: ElementsStore
) : ElementsRepository {
    override fun getElements() = store.elements

    override suspend fun removeElement(id: Int) {
        withContext(dispatcher.io) {
            store.removeAt(id)
        }
    }
}