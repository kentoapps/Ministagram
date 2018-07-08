package com.kentoapps.ministagram.ui.comment

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.util.DiffUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.kentoapps.ministagram.data.model.Comment
import com.kentoapps.ministagram.databinding.CellCommentBinding

class CommentAdapter(private val viewModel: CommentViewModel) : ListAdapter<Comment, CommentAdapter.ViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CellCommentBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(viewModel, binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val viewModel: CommentViewModel, private val binding: CellCommentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(comment: Comment) {
            binding.item = comment
            binding.vm = viewModel
        }
    }

    companion object {
        private val DIFF_CALLBACK: DiffUtil.ItemCallback<Comment> = object : DiffUtil.ItemCallback<Comment>() {
            override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
                return oldItem == newItem
            }
        }
    }
}
