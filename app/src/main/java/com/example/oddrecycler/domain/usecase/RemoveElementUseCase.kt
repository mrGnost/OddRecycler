package com.example.oddrecycler.domain.usecase

import com.example.oddrecycler.domain.repository.ElementsRepository
import javax.inject.Inject

class RemoveElementUseCase @Inject constructor(val repository: ElementsRepository) {
    suspend operator fun invoke(id: Int) = repository.removeElement(id)
}