package org.example.kotlin_chat_app.domain.usecase

import org.example.kotlin_chat_app.domain.repo.FirebaseAuthRepo

class FirebaseCreateWithEmailAndPasswordUseCase (val repo: FirebaseAuthRepo){

    suspend fun execute(email: String, password: String) {
        repo.createUserWithEmailAndPassword(email,password)
    }

}