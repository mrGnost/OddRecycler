package com.example.oddrecycler.data.di

import com.example.oddrecycler.data.repository.ElementsRepositoryImpl
import com.example.oddrecycler.domain.repository.ElementsRepository
import com.example.oddrecycler.util.Dispatcher
import com.example.oddrecycler.util.DispatcherImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class BindingsModule {
    @Binds
    abstract fun bindElementsRepository(repository: ElementsRepositoryImpl): ElementsRepository
    @Binds
    abstract fun bindDispatcher(dispatcher: DispatcherImpl): Dispatcher
}