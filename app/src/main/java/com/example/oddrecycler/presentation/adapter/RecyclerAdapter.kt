package com.example.oddrecycler.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.oddrecycler.data.entity.Element
import com.example.oddrecycler.R
import com.example.oddrecycler.presentation.util.RecyclerDiffCallback
import com.google.android.material.card.MaterialCardView

class RecyclerAdapter(
    private val onDelete: (index: Int) -> Unit
) : Adapter<RecyclerViewHolder>() {
    var data: List<Element> = listOf()
        set(value) {
            val callback = RecyclerDiffCallback(field, value)
            field = value.toList()
            val diff = DiffUtil.calculateDiff(callback)
            diff.dispatchUpdatesTo(this)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return RecyclerViewHolder(layoutInflater.inflate(R.layout.list_element, parent, false), onDelete)
    }

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        holder.bind(data[position])
    }
}

class RecyclerViewHolder(itemView: View, val onLongPressed: (id: Int) -> Unit) : ViewHolder(itemView) {
    private val root: MaterialCardView = itemView.findViewById(R.id.root)
    private val id: TextView = itemView.findViewById(R.id.name)

    fun bind(element: Element) {
        id.text = element.id.toString()
        root.setOnLongClickListener {
            onLongPressed(element.id)
            true
        }
    }
}