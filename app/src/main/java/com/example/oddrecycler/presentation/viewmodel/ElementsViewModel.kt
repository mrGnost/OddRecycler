package com.example.oddrecycler.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oddrecycler.data.repository.ElementsRepositoryImpl
import com.example.oddrecycler.domain.entity.Element
import com.example.oddrecycler.domain.usecase.RemoveElementUseCase
import com.example.oddrecycler.presentation.adapter.RecyclerAdapter
import com.example.oddrecycler.util.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ElementsViewModel @Inject constructor(
    private val repository: ElementsRepositoryImpl,
    private val scope: CoroutineScope,
    val removeElementUseCase: RemoveElementUseCase,
    val dispatcher: Dispatcher
) : ViewModel() {
    val adapter = RecyclerAdapter() { id ->
        removeElement(id)
    }

    private val elements: Flow<List<Element>>
        get() = repository.getElements()

    private fun removeElement(id: Int) {
        viewModelScope.launch {
            removeElementUseCase(id)
        }
    }

    fun startDataObserver() {
        scope.launch(dispatcher.default) {
            elements.collect {
                withContext(dispatcher.ui) {
                    updateRecyclerData(it)
                }
            }
        }
    }

    private fun updateRecyclerData(elements: List<Element>) {
        adapter.apply {
            data = elements
        }
    }
}