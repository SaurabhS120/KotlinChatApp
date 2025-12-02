package org.example.project.data.repo_impl

import org.example.project.data.datasource.FirebaseAuthDataSource
import org.example.project.domain.repo.FirebaseAuthRepo
import org.koin.core.component.KoinComponent
import org.koin.core.component.get

class FirebaseAuthRepoImpl(val dataSource : FirebaseAuthDataSource): FirebaseAuthRepo, KoinComponent {
    override suspend fun signInWithEmailAndPassword(email: String, password: String): Boolean {
        dataSource.signInWithEmailAndPassword(email, password)
        return  true
    }
}