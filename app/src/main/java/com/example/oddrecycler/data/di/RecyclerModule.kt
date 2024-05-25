package com.example.oddrecycler.data.di

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.oddrecycler.R
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped

@Module
@InstallIn(ActivityComponent::class)
class RecyclerModule {
    @Provides
    @ActivityScoped
    fun provideLayoutManager(@ActivityContext context: Context): GridLayoutManager {
        return GridLayoutManager(
            context,
            context.resources.getInteger(R.integer.elements_columns)
        )
    }
}