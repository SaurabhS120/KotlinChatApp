import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import org.example.project.domain.usecase.FirebaseCreateWithEmailAndPasswordUseCase
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun RegisterPagePreview() {
    RegisterPage(onBack = {})
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterPage(onBack: () -> Unit) {
    val viewModel:RegisterViewModel = koinViewModel()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            scope.launch {
                when(event){
                    is RegisterFailure -> {
                        snackbarHostState.showSnackbar(event.message)
                    }
                    is RegisterSuccess -> {
                        snackbarHostState.showSnackbar("Register Success")
                        onBack()
                    }
                    is RegisterInfo -> {
                        snackbarHostState.showSnackbar(event.message)
                    }
                }
            }
        }
    }
    Scaffold ( snackbarHost = { SnackbarHost(snackbarHostState) }, topBar = {
        TopAppBar(title = { Text("Register") },
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            })
    }){
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Register Page",fontSize = 36.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(48.dp))
            Column {
                Text("email")
                TextField(
                    value = viewModel.email.value,
                    onValueChange = { newText -> viewModel.email.value = newText },
                    label = { Text("Enter text") }
                )
                Spacer(modifier = Modifier.height(12.dp))
                Text("Password")
                TextField(
                    value = viewModel.password.value,
                    onValueChange = { newText -> viewModel.password.value = newText },
                    label = { Text("Enter text") }
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = {scope.launch { viewModel.register() }}, modifier = Modifier.padding(12.dp)){
                Text("Register", modifier = Modifier.padding(horizontal = 12.dp))
            }
            Text("email : ${viewModel.email.value}")
            Text("password : ${viewModel.password.value}")
        }
    }
}
class RegisterViewModel(val firebaseCreateWithEmailAndPasswordUseCase: FirebaseCreateWithEmailAndPasswordUseCase) : ViewModel() {
    private val _events = MutableSharedFlow<RegisterState>()
    val events = _events.asSharedFlow()
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    suspend fun register(){
        _events.emit(RegisterInfo("Register - email: ${email.value}, password: ${password.value}"))
        try {
            firebaseCreateWithEmailAndPasswordUseCase.execute(email.value, password.value)
            _events.emit(RegisterSuccess())
        } catch (e: Exception) {
            _events.emit(RegisterFailure("Register Failed: ${e.message}"))
        }
    }
}
sealed class RegisterState{}
class RegisterSuccess : RegisterState()
class RegisterFailure(val message: String) : RegisterState()
class RegisterInfo(val message: String) : RegisterState()
