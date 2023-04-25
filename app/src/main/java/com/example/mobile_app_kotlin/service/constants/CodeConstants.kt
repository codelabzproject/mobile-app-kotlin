package com.example.mobile_app_kotlin.service.constants

/**
 * Constantes usadas na aplicação
 */
class CodeConstants private constructor() {

    // SharedPreferences
    object SHARED {
        const val TOKEN_KEY = "tokenkey"
        const val USER_NAME = "username"
        const val USER_AVATAR = "useravatar"
    }

    // Requisições API
    object HEADER {
        const val TOKEN_KEY = "token"
        const val PERSON_KEY = "personkey"
    }

    object HTTP {
        const val SUCCESS = 200
        const val CREATED = 201
        const val BADREQUEST = 404
    }

    object BUNDLE {
        const val TASKID = "taskid"
        const val CODEFILTER = "codefilter"
    }

    // Filtro de tarefas
    object FILTER {
        const val ALL = 0
        const val NEXT = 1
        const val EXPIRED = 2
    }

}
