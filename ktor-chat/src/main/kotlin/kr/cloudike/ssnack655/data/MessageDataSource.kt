package kr.cloudike.ssnack655.data

import kr.cloudike.ssnack655.data.model.Message

interface MessageDataSource {

    suspend fun getAllMessages(): List<Message>

    suspend fun insertMessage(message: Message)
}