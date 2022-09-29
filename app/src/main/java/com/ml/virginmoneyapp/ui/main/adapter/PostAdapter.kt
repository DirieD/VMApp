package com.ml.virginmoneyapp.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ml.virginmoneyapp.databinding.ListItemPostBinding
import com.ml.virginmoneyapp.domain.Room

class PostAdapter(val postClick: (Room) -> Unit) :
    PagingDataAdapter<Room, PostAdapter.PostViewHolder>(DIFF_CALLBACK) {

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        getItem(position)?.let { post ->
            holder.bindTo(post)
            holder.itemView.setOnClickListener { postClick(post) }
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding = ListItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return PostViewHolder(binding)
    }

    class PostViewHolder(private val binding: ListItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(room: Room) {
            with(room) {
                binding.authorNameTextview.text = title
                binding.postTitleTextview.text = author
            }
        }
    }
}


val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Room>() {
    override fun areItemsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem.title == newItem.title &&
                oldItem.author == newItem.author
    }

    override fun areContentsTheSame(oldItem: Room, newItem: Room): Boolean {
        return oldItem == newItem
    }
}