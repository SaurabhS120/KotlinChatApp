package org.example.project.domain.usecase

import org.example.project.domain.repo.FirebaseAuthRepo

class FirebaseSignInWithEmailAndPasswordUseCase (val repo: FirebaseAuthRepo){

    suspend fun execute(email: String, password: String) {
        repo.signInWithEmailAndPassword(email,password)
    }

}