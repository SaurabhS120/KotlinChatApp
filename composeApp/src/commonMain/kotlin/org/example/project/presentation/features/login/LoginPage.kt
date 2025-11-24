import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinchatapp.composeapp.generated.resources.Res
import kotlinchatapp.composeapp.generated.resources.compose_multiplatform
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import org.example.project.Greeting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.*

@Preview
@Composable
fun LoginPage(){
    val viewModel = viewModel { LoginViewModel() }
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
                Text("Username")
                TextField(
                    value = viewModel.userName.value,
                    onValueChange = { newText -> viewModel.userName.value = newText },
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
            Text("username : ${viewModel.userName.value}")
            Text("password : ${viewModel.password.value}")
        }
    }
}
class LoginViewModel : ViewModel() {
    private val _events = MutableSharedFlow<String>()
    val events = _events.asSharedFlow()
    var userName = mutableStateOf("")
    var password = mutableStateOf("")
    suspend fun login(){
        _events.emit("Login  - UserName : ${userName.value},Password : ${password.value}")
        try {
            val auth = Firebase.auth
            auth.signInWithEmailAndPassword(userName.value, password.value)
            _events.emit("Login Successful")
        } catch (e: Exception) {
            _events.emit("Login Failed: ${e.message}")
        }
    }
}