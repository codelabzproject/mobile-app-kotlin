package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentSelectAvatarBinding
import com.example.mobile_app_kotlin.databinding.ItemAvatarBinding
import com.example.mobile_app_kotlin.service.listener.AvatarListener
import com.example.mobile_app_kotlin.service.model.response.AvatarModel
import com.example.mobile_app_kotlin.view.viewholder.AvatarViewHolder

class AvatarAdapter : RecyclerView.Adapter<AvatarViewHolder>() {

    private var listAvatars: List<AvatarModel> = arrayListOf()
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION
    private var listener: AvatarListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = ItemAvatarBinding.inflate(inflater, parent, false)
        return AvatarViewHolder(itemBinding, listener, parent.context)
    }

    override fun onBindViewHolder(holder: AvatarViewHolder, position: Int) {
        val avatar = listAvatars[position]
        holder.bindData(avatar)

        // Definir a borda para o item selecionado
        if (position == selectedItemPosition) {
            holder.setSelected(true)
        } else {
            holder.setSelected(false)
        }

        holder.itemView.setOnClickListener {
            setSelectedItemPosition(position)
            listener?.onClickAvatar(position)
        }
    }
    override fun getItemCount(): Int {
        return listAvatars.size
    }

    fun getItem(position: Int): AvatarModel {
        return listAvatars[position]
    }

    fun updateAvatares(list: List<AvatarModel>) {
        listAvatars = list
        notifyDataSetChanged()
    }

    fun attachListener(postListener: AvatarListener) {
        listener = postListener
    }

    private fun setSelectedItemPosition(position: Int) {
        val previousPosition = selectedItemPosition
        selectedItemPosition = position
        if (previousPosition != RecyclerView.NO_POSITION) {
            notifyItemChanged(previousPosition)
        }
        notifyItemChanged(selectedItemPosition)
    }
}