package kr.co.study.ktor_client_tutorial

import io.ktor.client.request.*
import io.ktor.utils.io.core.*
import kotlin.io.use

object UserRepo {

    suspend fun fetchUsers(): List<User> {

        val url = "https://615075ada706cd00179b745c.mockapi.io/users"

        //return KtorClient.httpClient.get(url)

        return KtorClient.httpClient.use {
            it.get(url)
        }
    }

}