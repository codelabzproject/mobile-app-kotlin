package com.example.mobile_app_kotlin.service.repository.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mobile_app_kotlin.service.model.response.TopicModel

@Database(entities = [TopicModel::class], version = 1)
abstract class CodeDatabase : RoomDatabase() {

    abstract fun topicDAO(): TopicDAO

    companion object {
        private lateinit var INSTANCE: CodeDatabase

        fun getDatabase(context: Context): CodeDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                synchronized(CodeDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context, CodeDatabase::class.java, "codeDB")
                        .allowMainThreadQueries().build()
                }
            }
            return INSTANCE
        }


    }
}