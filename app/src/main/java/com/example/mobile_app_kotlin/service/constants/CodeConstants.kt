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
        const val USER_PSWD = "userpswd"
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

    object AVATAR {
        const val avatar1 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar1.png"
        const val avatar2 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar2.png"
        const val avatar3 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar3.png"
        const val avatar4 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar4.png"
        const val avatar5 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar5.png"
        const val avatar6 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar6.png"
        const val avatar7 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar7.png"
        const val avatar8 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar8.png"
        const val avatar9 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar9.png"
        const val avatar10 = "https://raw.githubusercontent.com/codelabzproject/public/main/imgpng/avatar10.png"
    }


    object AUX {
        const val isCreatePage = "isCreatePage"

    }

}
