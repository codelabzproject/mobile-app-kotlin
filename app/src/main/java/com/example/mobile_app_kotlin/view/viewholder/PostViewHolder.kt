package com.example.mobile_app_kotlin.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentPostTimelineBinding
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.service.model.response.PostModel

class PostViewHolder(
    private val itemBinding: FragmentPostTimelineBinding,
    private val codeListener: CodeListener?
) : RecyclerView.ViewHolder(itemBinding.root) {

    // Atribui valores aos elementos de interface
    fun bindData(postModel: PostModel, position: Int, isSelected: Boolean) {
        itemBinding.titlePost.text = postModel.title
        itemBinding.contentPost.text = postModel.content
        itemBinding.commentsPosts.text = postModel.comments.toString()
        itemBinding.countLikes.text = postModel.points.toString()
    }

    fun onClickLikeButton() {
        itemBinding.likePostButton.setOnClickListener {
            codeListener?.onClickLikeButton(adapterPosition)
        }
    }

    fun onClickPost() {
        itemBinding.root.setOnClickListener {
            codeListener?.onClickPost(adapterPosition)
        }
    }

    fun onClickDislikeButton() {
        itemBinding.dislikePostButton.setOnClickListener {
            codeListener?.onClickDislikeButton(adapterPosition)
        }
    }
}
