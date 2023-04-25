package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentCardTimelineBinding
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.view.viewholder.CodeViewHolder

class CodeAdapter : RecyclerView.Adapter<CodeViewHolder>() {

    private var listPosts: List<PostModel> = arrayListOf()
    private lateinit var listener: CodeListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CodeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FragmentCardTimelineBinding.inflate(inflater, parent, false)
        return CodeViewHolder(itemBinding, listener)
    }

    override fun onBindViewHolder(holder: CodeViewHolder, position: Int) {
        holder.bindData(listPosts[position])
    }

    override fun getItemCount(): Int {
        return listPosts.count()
    }

    fun updatePosts(list: List<PostModel>) {
        listPosts = list
        notifyDataSetChanged()
    }

    fun attachListener(taskListener: CodeListener) {
        listener = taskListener
    }

}