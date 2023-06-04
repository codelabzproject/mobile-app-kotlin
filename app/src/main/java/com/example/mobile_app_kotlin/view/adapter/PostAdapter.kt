package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentPostTimelineBinding
import com.example.mobile_app_kotlin.service.listener.PostListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.service.model.response.RiseModel
import com.example.mobile_app_kotlin.view.viewholder.PostViewHolder

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var listPosts: List<PostModel> = arrayListOf()
    private var riseModel: RiseModel? = null
    private var listener: PostListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FragmentPostTimelineBinding.inflate(inflater, parent, false)
        return PostViewHolder(itemBinding, listener, parent.context)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val card = listPosts[position]

        holder.bindData(card)
        holder.onClickLikeButton(card, riseModel)

        holder.onClickPost()
//        holder.onClickLikeButton(card, riseModel)
    }

    override fun getItemCount(): Int {
        return listPosts.size
    }

    fun getItem(position: Int): PostModel {
        return listPosts[position]
    }

    fun updatePosts(list: List<PostModel>) {
        listPosts = list
        notifyDataSetChanged()
    }

    fun updateRiseModel(it: RiseModel) {
        riseModel = it
        notifyDataSetChanged()
    }

    fun attachListener(postListener: PostListener) {
        listener = postListener
    }
}