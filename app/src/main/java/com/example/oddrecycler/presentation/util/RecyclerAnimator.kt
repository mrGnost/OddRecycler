package com.example.oddrecycler.presentation.util

import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.RecyclerView

class RecyclerAnimator : DefaultItemAnimator() {
    override fun animateAppearance(
        viewHolder: RecyclerView.ViewHolder,
        preLayoutInfo: ItemHolderInfo?,
        postLayoutInfo: ItemHolderInfo
    ): Boolean {
        animateMove(viewHolder, 0, postLayoutInfo.top, postLayoutInfo.left, postLayoutInfo.top)
        runPendingAnimations()
        return true
    }
}