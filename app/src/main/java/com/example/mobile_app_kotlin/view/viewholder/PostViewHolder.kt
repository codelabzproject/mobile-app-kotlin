package com.example.mobile_app_kotlin.view.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.FragmentPostTimelineBinding
import com.example.mobile_app_kotlin.service.listener.PostListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.RiseModel
import com.squareup.picasso.Picasso

class PostViewHolder(
    private val itemBinding: FragmentPostTimelineBinding,
    private val postListener: PostListener?,
    private val context: Context,
) : RecyclerView.ViewHolder(itemBinding.root) {

    // Atribui valores aos elementos de interface
    fun bindData(postModel: PostModel) {
        itemBinding.titlePost.text = postModel.title
        itemBinding.contentPost.text = postModel.content
        itemBinding.countLikes.text = postModel.points.toString()

        val userInfoPost = context.getString(
            R.string.enviado_por_user_em_data,
            postModel.user.name,
            context.getString(R.string.algum_dia)
        )
        itemBinding.userInfoPost.text = userInfoPost

        val qtdCommentsPost = context.getString(
            R.string.qtd_comentarios,
            postModel.comments.toString()
        )
        itemBinding.commentsPosts.text = qtdCommentsPost

        itemBinding.nameTopic.text = postModel.topic?.name

        val imageLike = if (postModel.userHasVoted) {
            R.drawable.icon_upvote_enabled
        } else {
            R.drawable.icon_upvote_disabled
        }
        itemBinding.likePostButton.setImageResource(imageLike)

        Picasso.get()
            .load(postModel.topic?.image)
            .into(itemBinding.svgTopicPost)

    }

    fun onClickLikeButton(postModel: PostModel, riseModel: RiseModel?) {
        itemBinding.likePostButton.setOnClickListener {
            postListener?.onClickLikeButton(adapterPosition, postModel.idPost)
        }


    }

    fun onClickPost() {
        itemBinding.root.setOnClickListener {
            postListener?.onClickPost(adapterPosition)
        }
    }
}
