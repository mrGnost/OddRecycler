package com.example.oddrecycler.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oddrecycler.data.entity.Element
import com.example.oddrecycler.data.repository.ElementsRepositoryImpl
import com.example.oddrecycler.domain.usecase.RemoveElementUseCase
import com.example.oddrecycler.presentation.adapter.RecyclerAdapter
import com.example.oddrecycler.util.Dispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ElementsViewModel @Inject constructor(
    val repository: ElementsRepositoryImpl,
    val removeElementUseCase: RemoveElementUseCase,
    val scope: CoroutineScope,
    val dispatcher: Dispatcher
) : ViewModel() {
    val adapter = RecyclerAdapter() { id ->
        removeElement(id)
    }

    val elements: SharedFlow<List<Element>>
        get() = repository.getElements()

    fun removeElement(id: Int) {
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
        Log.d("ACTIVITY", "new data: $elements")
        adapter.apply {
            data = elements
        }
    }
}