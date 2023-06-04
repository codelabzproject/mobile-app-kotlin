package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentCardComentarioBinding
import com.example.mobile_app_kotlin.service.listener.CommentListener
import com.example.mobile_app_kotlin.service.model.response.CommentModel
import com.example.mobile_app_kotlin.service.model.response.RiseModel
import com.example.mobile_app_kotlin.view.viewholder.CommentViewHolder

class CommentAdapter : RecyclerView.Adapter<CommentViewHolder>() {

    private var listComments: List<CommentModel> = arrayListOf()
    private var riseModel: RiseModel? = null
    private var listener: CommentListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CommentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FragmentCardComentarioBinding.inflate(inflater, parent, false)
        return CommentViewHolder(itemBinding, listener, parent.context)
    }

    override fun onBindViewHolder(holder: CommentViewHolder, position: Int) {
        val comment = listComments[position]
        holder.bindData(comment)
        holder.onClickLikeButton(comment, riseModel)

        // Configurar os elementos do cartão na ViewHolder
//        holder.onClickPost()
//        holder.onClickLikeButton(listPosts[position])
//        holder.onClickDislikeButton()
    }

    override fun getItemCount(): Int {
        return listComments.count()
    }

    fun getItem(position: Int): CommentModel {
        return listComments[position]
    }

    fun updateComments(list: MutableList<CommentModel>) {
        listComments = list.asReversed()
        notifyDataSetChanged()
    }

    fun updateRiseModel(it: RiseModel) {
        riseModel = it
        notifyDataSetChanged()
    }

    fun attachListener(commentListener: CommentListener) {
        listener = commentListener
    }

}