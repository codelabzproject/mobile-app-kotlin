package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentPostTimelineBinding
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.service.model.response.PostModel
import com.example.mobile_app_kotlin.view.viewholder.PostViewHolder

class PostAdapter : RecyclerView.Adapter<PostViewHolder>() {

    private var listPosts: List<PostModel> = arrayListOf()
    private var codeListener: CodeListener? = null
    private var selectedPosition: Int = RecyclerView.NO_POSITION

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FragmentPostTimelineBinding.inflate(inflater, parent, false)
        return PostViewHolder(itemBinding, codeListener)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
//        val card = listPosts[position]
        val card = listPosts[position]
        holder.bindData(listPosts[position], position, position == selectedPosition)

        // Configurar os elementos do cartão na ViewHolder
        // ...
        holder.onClickPost()
        holder.onClickLikeButton()
        holder.onClickDislikeButton()

        holder.itemView.setOnClickListener {
            updateSelectedPosition(position)
        }
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

    private fun updateSelectedPosition(position: Int) {
        val previousSelectedPosition = selectedPosition
        selectedPosition = position
        notifyItemChanged(previousSelectedPosition)
        notifyItemChanged(selectedPosition)
    }

    fun attachListener(taskListener: CodeListener) {
        codeListener = taskListener
    }

}