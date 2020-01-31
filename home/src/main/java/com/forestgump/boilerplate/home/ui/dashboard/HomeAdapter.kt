package com.forestgump.boilerplate.home.ui.dashboard

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.forestgump.boilerplate.core.domain.models.Document
import com.forestgump.boilerplate.home.R
import javax.inject.Inject

class HomeAdapter @Inject constructor() :
        ListAdapter<Document, HomeAdapter.ViewHolder>(FileDiff()) {

    var onItemClicked = { _: Document -> }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return ViewHolder(inflater.inflate(R.layout.item_home_file, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(holder.adapterPosition))
        holder.itemView.setOnClickListener {
            onItemClicked(getItem(holder.adapterPosition))
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val detectedText: TextView = view.findViewById(R.id.tv_detected_text)

        @SuppressLint("SetTextI18n")
        fun bind(storageFile: Document) {
            detectedText.text = storageFile.extractedText
        }
    }

    private class FileDiff : DiffUtil.ItemCallback<Document>() {
        override fun areItemsTheSame(oldItem: Document, newItem: Document): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Document, newItem: Document): Boolean {
            return (oldItem.id == newItem.id && oldItem.extractedText == newItem.extractedText)
        }
    }
}
