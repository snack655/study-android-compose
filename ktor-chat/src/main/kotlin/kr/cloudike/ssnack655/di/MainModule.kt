package kr.cloudike.ssnack655.di

import kr.cloudike.ssnack655.data.MessageDataSource
import kr.cloudike.ssnack655.data.MessageDataSourceImpl
import kr.cloudike.ssnack655.room.RoomController
import org.koin.dsl.module
import org.litote.kmongo.coroutine.coroutine
import org.litote.kmongo.reactivestreams.KMongo.createClient

val mainModule = module {
    single {
        createClient()
            .coroutine
            .getDatabase("message_db_yt")
    }
    single<MessageDataSource> {
        MessageDataSourceImpl(get())
    }
    single {
        RoomController(get())
    }
}