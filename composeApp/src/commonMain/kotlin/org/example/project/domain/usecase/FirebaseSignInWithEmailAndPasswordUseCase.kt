package org.example.kotlin_chat_app.domain.usecase

import org.example.kotlin_chat_app.domain.repo.FirebaseAuthRepo

class FirebaseSignInWithEmailAndPasswordUseCase (val repo: FirebaseAuthRepo){

    suspend fun execute(email: String, password: String) {
        repo.signInWithEmailAndPassword(email,password)
    }

}