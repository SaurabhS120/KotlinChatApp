package org.example.project.domain.usecase

import org.example.project.domain.repo.FirebaseAuthRepo

class FirebaseCreateWithEmailAndPasswordUseCase (val repo: FirebaseAuthRepo){

    suspend fun execute(email: String, password: String) {
        repo.createUserWithEmailAndPassword(email,password)
    }

}