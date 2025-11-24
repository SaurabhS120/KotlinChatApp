
sealed class Screens(val route: String){
    data object Login : Screens("Login")
    data object Register : Screens("Register")
}