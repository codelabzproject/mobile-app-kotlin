package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentCardTimelineBinding
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.view.viewholder.PostViewHolder

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var listPosts: List<PostModel> = arrayListOf()
    private var codeListener: CodeListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FragmentCardTimelineBinding.inflate(inflater, parent, false)
        return PostViewHolder(itemBinding, codeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bindData(listPosts[position], position)
    }

    override fun getItemCount(): Int {
        return listPosts.count()
    }

    fun getItem(position: Int): PostModel {
        return listPosts[position]
    }

    fun updatePosts(list: List<PostModel>) {
        listPosts = list
        notifyDataSetChanged()
    }

    fun attachListener(taskListener: CodeListener) {
        codeListener = taskListener
    }

}