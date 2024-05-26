package com.example.oddrecycler.presentation.util

import androidx.recyclerview.widget.DiffUtil
import com.example.oddrecycler.domain.entity.Element

class RecyclerDiffCallback(
    private val oldItems: List<Element>,
    private val newItems: List<Element>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldItems.size

    override fun getNewListSize(): Int = newItems.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition] == newItems[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldItems[oldItemPosition].id == newItems[newItemPosition].id
    }
}