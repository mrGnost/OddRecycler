package com.example.oddrecycler.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.oddrecycler.data.entity.Element
import com.example.oddrecycler.data.repository.ElementsRepositoryImpl
import com.example.oddrecycler.domain.usecase.RemoveElementUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ElementsViewModel @Inject constructor(
    val repository: ElementsRepositoryImpl,
    val removeElementUseCase: RemoveElementUseCase
) : ViewModel() {
    val elements: SharedFlow<List<Element>>
        get() = repository.getElements()

    fun removeElement(id: Int) {
        viewModelScope.launch {
            removeElementUseCase(id)
        }
    }
}