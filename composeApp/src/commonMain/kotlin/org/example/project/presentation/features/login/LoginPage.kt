import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
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
import org.example.kotlin_chat_app.domain.usecase.FirebaseSignInWithEmailAndPasswordUseCase
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.viewmodel.koinViewModel

@Preview
@Composable
fun LoginPagePreview() {
    LoginPage(createAccount = {})
}

@Composable
fun LoginPage(createAccount: ()->Unit){
    val viewModel:LoginViewModel =  koinViewModel()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            scope.launch {
                snackbarHostState.showSnackbar(event)
            }
        }
    }
    Scaffold ( snackbarHost = { SnackbarHost(snackbarHostState) }){
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text("Login Page",fontSize = 36.sp, fontWeight = FontWeight.Bold)
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
            Button(onClick = {scope.launch { viewModel.login() }}, modifier = Modifier.padding(12.dp)){
                Text("Login", modifier = Modifier.padding(horizontal = 12.dp))
            }
            Text("email : ${viewModel.email.value}")
            Text("password : ${viewModel.password.value}")
            Button(onClick = createAccount, modifier = Modifier.padding(12.dp)){
                Text("Create account", modifier = Modifier.padding(horizontal = 12.dp))
            }
        }
    }
}
class LoginViewModel(val signInWithEmailAndPasswordUseCase: FirebaseSignInWithEmailAndPasswordUseCase) : ViewModel() {
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    suspend fun login(){
        _events.emit("Login  - email : ${email.value},Password : ${password.value}")
        try {
            signInWithEmailAndPasswordUseCase.execute(email.value, password.value)
            _events.emit("Login Successful")
        } catch (e: Exception) {
            _events.emit("Login Failed: ${e.message}")
        }
    }
}