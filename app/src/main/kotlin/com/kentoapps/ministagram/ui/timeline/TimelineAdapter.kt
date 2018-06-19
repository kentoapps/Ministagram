package com.kentoapps.ministagram.ui.timeline

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kentoapps.ministagram.data.model.Post
import com.kentoapps.ministagram.databinding.CellPostBinding
import com.kentoapps.ministagram.util.extension.Suffix
import com.kentoapps.ministagram.util.extension.withSuffix

class TimelineAdapter(private val viewModel: TimelineViewModel) : ListAdapter<Post, TimelineAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CellPostBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(viewModel, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val viewModel: TimelineViewModel, private val binding: CellPostBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Post) {
            binding.item = item
            binding.vm = viewModel
            binding.likeNumText.text = item.likeUsers.size.withSuffix(Suffix.LIKE)
            binding.commentNumText.text = item.numOfComments.withSuffix(Suffix.COMMENT)
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Post> = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
                return oldItem == newItem
            }
        }
    }

}