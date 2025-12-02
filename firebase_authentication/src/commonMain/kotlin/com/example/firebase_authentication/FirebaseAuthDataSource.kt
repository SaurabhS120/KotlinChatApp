package com.example.firebase_authentication

import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.AuthResult
import dev.gitlive.firebase.auth.auth

class FirebaseAuthDataSource {
    val auth = Firebase.auth
    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        auth.signInWithEmailAndPassword(email, password)
        return  true
    }
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Boolean {
        auth.createUserWithEmailAndPassword(email, password)
        return  true
    }
}