package com.example.mobile_app_kotlin.view.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentPostTimelineBinding
import com.example.mobile_app_kotlin.service.listener.PostListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.squareup.picasso.Picasso

class PostViewHolder(
    private val itemBinding: FragmentPostTimelineBinding,
    private val postListener: PostListener?,
    private val context: Context,
) : RecyclerView.ViewHolder(itemBinding.root) {

    // Atribui valores aos elementos de interface
    fun bindData(postModel: PostModel, position: Int, isSelected: Boolean) {
        itemBinding.titlePost.text = postModel.title
        itemBinding.contentPost.text = postModel.content
        itemBinding.commentsPosts.text = postModel.comments.toString()
        itemBinding.countLikes.text = if (postModel.points >= 0) {
            postModel.points.toString()
        } else {
            "0"
        }

        val userInfoPost =
            context.getString(
                R.string.enviado_por_user_em_data,
                postModel.user.name,
                postModel.createdIn ?: "algum dia"
            )

        itemBinding.userInfoPost.text = userInfoPost
        itemBinding.nameTopic.text = postModel.topic?.name

        Picasso.get()
//            .load(postModel.topic?.image)
            .load("https://raw.githubusercontent.com/codelabzproject/public/main/img/avatar1.png")
            .into(itemBinding.svgTopicPost)
    }

    fun onClickLikeButton() {
        itemBinding.likePostButton.setOnClickListener {
            postListener?.onClickLikeButton(adapterPosition)
        }
    }

    fun onClickPost() {
        itemBinding.root.setOnClickListener {
            postListener?.onClickPost(adapterPosition)
        }
    }

    fun onClickDislikeButton() {
        itemBinding.dislikePostButton.setOnClickListener {
            postListener?.onClickDislikeButton(adapterPosition)
        }
    }
}
