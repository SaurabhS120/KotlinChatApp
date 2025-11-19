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
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import org.example.project.Greeting
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Preview
@Composable
fun LoginPage(){
    val viewModel = viewModel { LoginViewModel() }
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
        Button(onClick = {}, modifier = Modifier.padding(12.dp)){
            Text("Login", modifier = Modifier.padding(horizontal = 12.dp))
        }
        Text("username : ${viewModel.userName.value}")
        Text("password : ${viewModel.password.value}")
    }
}
class LoginViewModel : ViewModel() {
    var userName = mutableStateOf("")
    var password = mutableStateOf("")
}