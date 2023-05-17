package com.example.mobile_app_kotlin.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobile_app_kotlin.databinding.FragmentUserProfileBinding
import com.example.mobile_app_kotlin.service.listener.CodeListener
import com.example.mobile_app_kotlin.service.model.response.UserModel
import com.example.mobile_app_kotlin.view.viewholder.UserProfileViewHolder

class UserAdapter : RecyclerView.Adapter<UserProfileViewHolder>() {

    private lateinit var userProfile: UserModel
    private lateinit var listener: CodeListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserProfileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemBinding = FragmentUserProfileBinding.inflate(inflater, parent, false)
        return UserProfileViewHolder(itemBinding, listener)
    }


    override fun onBindViewHolder(holder: UserProfileViewHolder, position: Int) {
        holder.bindData(userProfile)
    }

    override fun getItemCount(): Int {
        return 0
    }

    fun updateUser(user: UserModel) {
        userProfile = user
        notifyDataSetChanged()
    }

    fun attachListener(taskListener: CodeListener) {
        listener = taskListener
    }

}