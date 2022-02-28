package kr.cloudike.ssnack655.plugins

import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.request.*
import kr.cloudike.ssnack655.room.RoomController
import kr.cloudike.ssnack655.routes.chatSocket
import kr.cloudike.ssnack655.routes.getAllMessages
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    val roomController by inject<RoomController>()
    install(Routing) {
        chatSocket(roomController = roomController)
        getAllMessages(roomController = roomController)
    }
}
