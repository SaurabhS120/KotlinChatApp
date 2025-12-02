package org.example.project

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.window.ComposeUIViewController
fun MainViewController() = ComposeUIViewController {
    LaunchedEffect(Unit) {
        DI.initialize()
    }
    App()
}