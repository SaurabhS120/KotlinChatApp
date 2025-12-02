package org.example.project.domain.repo

interface FirebaseAuthRepo {
    suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean
    suspend fun createUserWithEmailAndPassword(email: String, password: String): Boolean
}