package com.example.mobile_app_kotlin.service.constants

/**
 * Constantes usadas na aplicação
 */
class CodeConstants private constructor() {

    // SharedPreferences
    object SHARED {
        const val USER_NAME = "username"
        const val USER_AVATAR = "useravatar"
        const val NICK_NAME = "nickname"
        const val USER_ID = "userid"
    }

    // Requisições API
    object HEADER {
        const val TOKEN_KEY = "token"
        const val PERSON_KEY = "personkey"
    }

    object HTTP {
        const val SUCCESS = 200
        const val CREATED = 201
        const val EMPTY = 204
        const val BADREQUEST = 404
    }

    object TOPIC {
        const val TOPIC_ID = "topic_id"
        const val TOPIC_NAME = "topic_name"
    }
}
