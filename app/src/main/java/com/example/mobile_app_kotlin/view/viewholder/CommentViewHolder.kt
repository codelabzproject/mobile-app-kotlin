package com.example.mobile_app_kotlin.view.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentCardComentarioBinding
import com.example.mobile_app_kotlin.service.listener.CommentListener
import com.example.mobile_app_kotlin.service.model.response.CommentModel
import com.squareup.picasso.Picasso

class CommentViewHolder(
    private val itemBinding: FragmentCardComentarioBinding,
    private val commentListener: CommentListener?,
    private val context: Context,
) : RecyclerView.ViewHolder(itemBinding.root) {

    // Atribui valores aos elementos de interface
    fun bindData(commentModel: CommentModel) {
        itemBinding.contentComment.text = commentModel.content
        itemBinding.qtdLikesComment.text = commentModel.likes.toString()
        itemBinding.usernameUserComment.text = commentModel.user.nickname

        val sharedIn =
            context.getString(
                R.string.enviado_em,
//                commentModel.createdIn ?:
                "algum dia"
            )

        itemBinding.sharedIn.text = sharedIn

        Picasso.get()
//            .load(postModel.topic?.image)
            .load("https://raw.githubusercontent.com/codelabzproject/public/main/img/avatar1.png")
            .into(itemBinding.imageUserComment)

        Picasso.get()
            .load(R.drawable.heart_like_icon)
//            .load("https://raw.githubusercontent.com/codelabzproject/public/main/img/avatar1.png")
            .into(itemBinding.likeIcon)
    }

//    fun onClickLikeButton(postModel: PostModel) {
//        itemBinding.likePostButton.setOnClickListener {
//            postListener?.onClickLikeButton(adapterPosition)
//            itemBinding.countLikes.text = (postModel.points + 1).toString()
//        }
//    }
//
//    fun onClickPost() {
//        itemBinding.root.setOnClickListener {
//            postListener?.onClickPost(adapterPosition)
//        }
//    }
//
//    fun onClickDislikeButton() {
//        itemBinding.dislikePostButton.setOnClickListener {
//            postListener?.onClickDislikeButton(adapterPosition)
//        }
//    }
}
