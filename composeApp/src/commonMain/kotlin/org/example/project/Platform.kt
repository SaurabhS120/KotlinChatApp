package org.example.kotlin_chat_app

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform