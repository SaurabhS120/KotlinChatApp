package org.example.project.navigation

import Screens
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.example.project.MainActivity
import org.junit.Rule
import org.junit.Test
import kotlin.test.Test as KotlinTest

class NavigationTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Test
    fun initialRoute_shouldBeLoginPage() {
        composeTestRule.onNodeWithText("Login Page").assertIsDisplayed()
        composeTestRule.onNodeWithText("email").assertIsDisplayed()
        composeTestRule.onNodeWithText("Password").assertIsDisplayed()
        composeTestRule.onNodeWithText("Login").assertIsDisplayed()
    }

    @KotlinTest
    fun screenLoginRoute_shouldBeCorrect() {
        val loginRoute = Screens.Login.route
        assert(loginRoute == "Login") { "Expected login route to be 'Login' but was '$loginRoute'" }
    }
}
