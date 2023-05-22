package com.example.mobile_app_kotlin.view.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.TopicItemLayoutBinding
import com.example.mobile_app_kotlin.service.model.response.TopicModel

class TopicViewHolder (
    private val itemBinding: TopicItemLayoutBinding,
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindData(topicModel: TopicModel, position: Int) {
        itemBinding.nameTopic.text = topicModel.name
//        itemBinding.imageTopic.setImageResource(topicModel.image)

//        itemBinding.titlePost.text = postModel.title
//        itemBinding.contentPost.text = postModel.content
//        itemBinding.commentsPosts.text = postModel.comments.toString()
//        itemBinding.liskes.text = postModel.points.toString()

        itemBinding.root.setOnClickListener {
//            codeListener?.onClickPost(position)
        }

    }

}