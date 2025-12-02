package org.example.project

import org.example.project.data.datasource.FirebaseAuthDataSource
import org.example.project.data.repo_impl.FirebaseAuthRepoImpl
import org.example.project.domain.repo.FirebaseAuthRepo
import org.example.project.domain.usecase.FirebaseSignInWithEmailAndPasswordUseCase
import org.koin.core.context.startKoin
import org.koin.dsl.module

object DI {
    fun initialize(){
        val firebaseDataSource = module {
            single { FirebaseAuthDataSource() }
        }
        val repo = module {
            single<FirebaseAuthRepo> { FirebaseAuthRepoImpl(get()) }
        }
        val usecase = module {
            single {
                FirebaseSignInWithEmailAndPasswordUseCase(get())
            }
        }
        startKoin {
            modules(
                firebaseDataSource,
                repo,
                usecase,
            )
        }
    }
}