package com.example.mobile_app_kotlin.view.viewholder

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.R
import com.example.mobile_app_kotlin.databinding.ItemAvatarBinding
import com.example.mobile_app_kotlin.service.listener.AvatarListener
import com.example.mobile_app_kotlin.service.model.response.AvatarModel
import com.squareup.picasso.Picasso

class AvatarViewHolder(
    private val itemBinding: ItemAvatarBinding,
    private val listener: AvatarListener?,
    private val context: Context,
) : RecyclerView.ViewHolder(itemBinding.root) {

    // Atribui valores aos elementos de interface
    fun bindData(avatarModel: AvatarModel) {

        Picasso.get()
            .load(avatarModel.iconPng)
            .into(itemBinding.avatarImageView)

    }
    fun setSelected(selected: Boolean) {
        if (selected) {
            val context = itemBinding.root.context
            val selectedColor = ContextCompat.getColor(context, R.color.code_blue_normal)
            itemBinding.avatarImageView.setBackgroundColor(selectedColor)
        } else {
            itemBinding.avatarImageView.setBackgroundColor(0) // Remove a cor de fundo
        }
    }
}
