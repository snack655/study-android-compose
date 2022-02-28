package com.plcoding.ktorandroidchat.data.remote

import com.plcoding.ktorandroidchat.domain.model.Message

interface MessageService {

    suspend fun getAllMessage(): List<Message>

    companion object {
        const val BASE_URL = "http://192.168.0.14:8082"
    }

    sealed class Endpoints(val url: String) {
        object GetAllMessages: Endpoints("$BASE_URL/message")
    }
}