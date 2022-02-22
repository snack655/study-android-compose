package kr.co.study.ktor_client_tutorial

import androidx.annotation.Keep
import kotlinx.serialization.Serializable

@Keep
@Serializable
data class User (
    val createdAt: String,
    val name: String,
    val avatar: String,
    val id: String
)
