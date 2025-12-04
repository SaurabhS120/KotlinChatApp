package org.example.kotlin_chat_app.data.repo_impl

import com.example.firebase_authentication.FirebaseAuthDataSource
import org.example.kotlin_chat_app.domain.repo.FirebaseAuthRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FirebaseAuthRepoImpl(val dataSource : FirebaseAuthDataSource): FirebaseAuthRepo, KoinComponent {
    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        return dataSource.signInWithEmailAndPassword(email, password)
    }
    override suspend fun createUserWithEmailAndPassword(email: String, password: String): Boolean {
        return dataSource.createUserWithEmailAndPassword(email, password)
    }
}