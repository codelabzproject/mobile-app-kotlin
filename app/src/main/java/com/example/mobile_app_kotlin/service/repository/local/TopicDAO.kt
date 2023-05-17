package com.example.mobile_app_kotlin.service.repository.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.mobile_app_kotlin.service.model.response.TopicModel

@Dao
interface TopicDAO {

    @Insert
    fun saveList(listTopics: List<TopicModel>)

    @Query("SELECT * FROM Topic")
    fun getList(): List<TopicModel>

    @Query("DELETE FROM Topic")
    fun clear()
}