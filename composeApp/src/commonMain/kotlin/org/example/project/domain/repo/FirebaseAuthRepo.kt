package org.example.project.domain.repo

interface FirebaseAuthRepo {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean
}