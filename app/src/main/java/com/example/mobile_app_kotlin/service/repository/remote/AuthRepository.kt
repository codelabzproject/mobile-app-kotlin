package com.example.mobile_app_kotlin.service.repository.remote

import com.example.mobile_app_kotlin.service.listener.APIListener
import com.example.mobile_app_kotlin.service.model.UserModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AuthRepository {
    private val remote = RetrofitClient.getService(AuthService::class.java)

    fun login(email: String, password: String, listener: APIListener<UserModel>) {
        val userRequest = UserRequest(email, password)
        val call = remote.login(userRequest)

        call.enqueue(object  : Callback<UserModel>{
            override fun onResponse(call: Call<UserModel>, response: Response<UserModel>) {
                if (response.code() == 200){
                    val s = ""

                } else {
                    listener.onFailure("")

                }
//                listener.onSuccess()
            }

            override fun onFailure(call: Call<UserModel>, t: Throwable) {
                val s = ""
                listener.onFailure("")
            }
        })
    }
}
