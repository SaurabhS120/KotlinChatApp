### Navigation
We have used `NavHost` composable to add navigation in out chat app.

1. We have created [Screens Class](composeApp/src/commonMain/kotlin/org/example/project/presentation/navigation/Screens.kt) file which contains all navigation routes.
```
sealed class Screens(val route: String){
    data object Login : Screens("Login")
}
```
2. Created [NavGraph](composeApp/src/commonMain/kotlin/org/example/project/presentation/navigation/NavGraph.kt) composable function which contains all navigation. It required NavHostController and start destination  

```@Composable
fun NavGraph(navController: NavHostController){
    NavHost(navController=navController, startDestination = Screens.Login.route){
        composable (Screens.Login.route){
            LoginPage()
        }
    }
}
```
3. Added NavGraph composable in [App.kt](composeApp/src/commonMain/kotlin/org/example/project/App.kt) as a starting point for app.
```
@Composable
@Preview
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavGraph(navController)
    }
}
```
