package org.example.project
import LoginViewModel
import RegisterViewModel
import com.example.firebase_authentication.FirebaseAuthDataSource
import org.example.project.data.repo_impl.FirebaseAuthRepoImpl
import org.example.project.domain.repo.FirebaseAuthRepo
import org.example.project.domain.usecase.FirebaseCreateWithEmailAndPasswordUseCase
import org.example.project.domain.usecase.FirebaseSignInWithEmailAndPasswordUseCase
import org.koin.core.context.startKoin
import org.koin.core.module.dsl.viewModel
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
                FirebaseCreateWithEmailAndPasswordUseCase(get())
            }
            single {
                FirebaseSignInWithEmailAndPasswordUseCase(get())
            }
        }
        val viewModels = module{
            viewModel{
                RegisterViewModel(get())
            }
            viewModel{
                LoginViewModel(get())
            }
        }
        startKoin {
            modules(
                firebaseDataSource,
                repo,
                usecase,
                viewModels,
            )
        }
    }
}