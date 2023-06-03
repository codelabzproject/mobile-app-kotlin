package com.example.mobile_app_kotlin.view.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.TopicItemLayoutBinding
import com.example.mobile_app_kotlin.service.listener.TopicListener
import com.example.mobile_app_kotlin.service.model.response.TopicModel
import com.squareup.picasso.Picasso

class TopicViewHolder(
    private val itemBinding: TopicItemLayoutBinding,
    private val postListener: TopicListener?,
    private val context: Context,
) : RecyclerView.ViewHolder(itemBinding.root) {

    fun bindData(topicModel: TopicModel, position: Int) {
        itemBinding.nameTopic.text = topicModel.name

        Picasso.get()
            .load(topicModel.image)
            .into(itemBinding.imageTopic)
    }

    fun onClickTopic() {
        itemBinding.root.setOnClickListener {
            postListener?.onClickTopic(adapterPosition)
        }
    }

}