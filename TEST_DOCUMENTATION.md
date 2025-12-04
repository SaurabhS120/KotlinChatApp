# Navigation Test Documentation

## What is Being Tested

This test suite verifies the navigation functionality of the application, specifically:

### 1. Initial Route Navigation

**Test:** `initialRoute_shouldBeLoginPage()`

Verifies that the Login page is the initial screen when the app launches.

**Assertions:**

- ✅ "Login Page" title is displayed
- ✅ "email" input field is visible
- ✅ "Password" input field is visible
- ✅ "Login" button is displayed

### 2. Route Configuration

**Test:** `screenLoginRoute_shouldBeCorrect()`

Verifies that the Login screen route is correctly defined.

**Assertion:**

- ✅ `Screens.Login.route` equals `"Login"`

## Test Location

```
composeApp/src/androidInstrumentedTest/kotlin/org/example/project/navigation/NavigationTest.kt
```

## How Tests Were Added

### Step 1: Create Test Directory Structure

Created the Android instrumented test directory:

```
composeApp/src/androidInstrumentedTest/kotlin/org/example/project/navigation/
```

### Step 2: Add Dependencies to `composeApp/build.gradle.kts`

**Kotlin Multiplatform Source Sets:**

```kotlin
sourceSets {
    // Common test dependencies
    commonTest.dependencies {
        implementation(libs.kotlin.test)
        @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
        implementation(compose.uiTest)
    }
    
    // Android instrumented test dependencies
    val androidInstrumentedTest by getting {
        dependencies {
            implementation(libs.kotlin.test)
            @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
            implementation(compose.uiTest)
        }
    }
}
```

**Android Test Dependencies:**

```kotlin
dependencies {
    debugImplementation(compose.uiTooling)
    androidTestImplementation(libs.androidx.testExt.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
    androidTestImplementation(compose.uiTest)
    androidTestImplementation("androidx.compose.ui:ui-test-junit4:1.7.6")
}
```

**Test Runner Configuration:**

```kotlin
android {
    defaultConfig {
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}
```

### Step 3: Create Test Class

Created `NavigationTest.kt` with:

```kotlin
package org.example.kotlin_chat_app.navigation

import Screens
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.example.kotlin_chat_app.MainActivity
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
        assert(loginRoute == "Login") { 
            "Expected login route to be 'Login' but was '$loginRoute'" 
        }
    }
}
```

### Key Implementation Details

- **Uses `createAndroidComposeRule<MainActivity>()`**: Launches the real MainActivity instead of a
  test activity
- **JUnit 4 Rule**: Uses `@get:Rule` to properly set up the test environment
- **No manual `setContent` needed**: MainActivity automatically sets up the App composable
- **Compose Test API**: Uses `onNodeWithText()` and `assertIsDisplayed()` for UI verification

## How to Run the Tests

### Prerequisites

**Required:** An Android device or emulator must be connected and running.

Check connected devices:

```bash
adb devices
```

### Option 1: Run All Instrumented Tests

```bash
./gradlew :composeApp:connectedAndroidTest
```

### Option 2: Run Only NavigationTest Class

```bash
./gradlew :composeApp:connectedAndroidTest --tests "org.example.kotlin_chat_app.navigation.NavigationTest"
```

### Option 3: Run a Specific Test Method

```bash
./gradlew :composeApp:connectedAndroidTest --tests "org.example.kotlin_chat_app.navigation.NavigationTest.initialRoute_shouldBeLoginPage"
```

### Option 4: Run from Android Studio / IntelliJ IDEA

1. Open `NavigationTest.kt`
2. Click the green ▶️ icon next to the test class or method
3. Select "Run 'NavigationTest'"

### View Test Results

**HTML Report:**

```
composeApp/build/reports/androidTests/connected/index.html
```

**XML Results:**

```
composeApp/build/outputs/androidTest-results/connected/debug/
```

**Console Output:**

```bash
./gradlew :composeApp:connectedAndroidTest --info
```

## Additional Information

### Why Android Instrumented Tests?

Compose UI tests need a real Android environment to render composables. They cannot run as local JVM
unit tests.

### Test Technology Stack

| Component      | Technology                                 |
|----------------|--------------------------------------------|
| Test Framework | JUnit 4                                    |
| Test Type      | Android Instrumented Tests                 |
| UI Testing     | Compose UI Test API                        |
| Test Rule      | `createAndroidComposeRule<MainActivity>()` |
| Test Runner    | AndroidJUnitRunner                         |
| Library        | `androidx.compose.ui:ui-test-junit4:1.7.6` |

## Troubleshooting

### ❌ "No connected devices"

**Problem:** No Android device or emulator is connected.

**Solution:**

```bash
# Check connected devices
adb devices

# If no devices, start an emulator or connect a device
```

### ❌ "Unable to resolve activity" Error

**Error Message:**

```
java.lang.RuntimeException: Unable to resolve activity for: Intent { ... 
cmp=org.example.kotlin_chat_app.test/androidx.activity.ComponentActivity }
```

**Cause:** Using `runComposeUiTest` instead of `createAndroidComposeRule`.

**Solution:**

1. Use `createAndroidComposeRule<MainActivity>()` instead
2. Add dependency: `androidx.compose.ui:ui-test-junit4:1.7.6`
3. Import: `androidx.compose.ui.test.junit4.createAndroidComposeRule`
4. Use `@get:Rule` annotation with the test rule

### ❌ Test Assertion Failures

**Common Causes:**

- UI text has changed (tests are case-sensitive)
- Login page elements are not visible
- Navigation route configuration changed

**Debug Steps:**

```bash
# Run with verbose logging
./gradlew :composeApp:connectedAndroidTest --info

# Check the HTML report
open composeApp/build/reports/androidTests/connected/index.html
```

### ❌ Build/Compilation Errors

**Solution:**

```bash
# Clean and rebuild
./gradlew clean
./gradlew :composeApp:build

# In Android Studio: File > Invalidate Caches and Restart
```
