package org.example.project.data.datasource

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import dev.gitlive.firebase.auth.*

class FirebaseAuthDataSource {
    val auth = Firebase.auth
    suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return auth.signInWithEmailAndPassword(email, password)
    }
}