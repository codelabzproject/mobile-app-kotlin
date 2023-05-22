package com.example.mobile_app_kotlin.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentCardTimelineBinding
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.service.model.response.PostModel

class PostViewHolder(
    private val itemBinding: FragmentCardTimelineBinding,
    private val codeListener: CodeListener?,
) : RecyclerView.ViewHolder(itemBinding.root) {

    //  Atribui valores aos elementos de interface
    fun bindData(postModel: PostModel, position: Int) {

        itemBinding.titlePost.text = postModel.title
        itemBinding.contentPost.text = postModel.content
        itemBinding.commentsPosts.text = postModel.comments.toString()
        itemBinding.liskes.text = postModel.points.toString()

        itemBinding.root.setOnClickListener {
//            codeListener?.onClickPost(position)
        }

    }
}