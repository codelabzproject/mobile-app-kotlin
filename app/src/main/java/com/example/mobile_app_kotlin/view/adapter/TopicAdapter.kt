package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.TopicItemLayoutBinding
import com.example.mobile_app_kotlin.service.listener.TopicListener
import com.example.mobile_app_kotlin.service.model.response.TopicModel
import com.example.mobile_app_kotlin.view.viewholder.TopicViewHolder

class TopicAdapter : RecyclerView.Adapter<TopicViewHolder>() {

    private var listTopics: List<TopicModel> = arrayListOf()
    private var listener: TopicListener? = null
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopicViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = TopicItemLayoutBinding.inflate(inflater, parent, false)
        return TopicViewHolder(itemBinding, listener, parent.context)
    }

    override fun getItemCount(): Int {
        return listTopics.count()
    }

    override fun onBindViewHolder(holder: TopicViewHolder, position: Int) {

        holder.bindData(listTopics[position], position)

        holder.onClickTopic()

        holder.itemView.setOnClickListener {
            updateSelectedPosition(position)
        }
    }

    fun updateTopics(list: List<TopicModel>) {
        listTopics = list
        notifyDataSetChanged()
    }

    fun attachListener(topicListener: TopicListener) {
        listener = topicListener
    }

    private fun updateSelectedPosition(position: Int) {
        val previousSelectedPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousSelectedPosition)
        notifyItemChanged(selectedPosition)
    }
//    fun attachListener(taskListener: CodeListener) {
//        codeListener = taskListener
//    }
}