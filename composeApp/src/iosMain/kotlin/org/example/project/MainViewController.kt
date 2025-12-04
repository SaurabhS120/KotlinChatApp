package org.example.kotlin_chat_app

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.ComposeUIViewController
fun MainViewController() = ComposeUIViewController {
        DI.initialize()
    App()
}