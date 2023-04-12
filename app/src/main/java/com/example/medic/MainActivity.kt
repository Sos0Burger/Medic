package com.example.medic

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.medic.data.User
import com.example.medic.screens.*
import com.example.medic.ui.theme.MedicTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        User.sharedPrefs = getSharedPreferences("pref", Context.MODE_PRIVATE)
        User.email = User.sharedPrefs.getString("email", "")
        User.password = User.sharedPrefs.getString("password", "")
        User.token = User.sharedPrefs.getString("token", "")
        User.isCardCreated = User.sharedPrefs.getBoolean("isCardCreated", false)
        setContent {
            MedicTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Screen()
                }
            }
        }
    }
}

@Composable
fun Screen() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Gradient") {
        composable("Gradient") {
            if (User.token == "") GradientScreen(onNavigateToOnboard = {
                navController.navigate(
                    "Onboard"
                )
            })
            else if(User.password=="") navController.navigate("Password")
            else if (User.isCardCreated == false) navController.navigate("PatientCard")
            else navController.navigate("Main")
        }
        composable("Onboard") {
            OnboardScreen(onNavigateToRegistration = {
                navController.navigate(
                    "Registration"
                )
            })
        }
        composable("Registration") {
            RegistrationScreen(onNavigateToEmailCode = {
                navController.navigate(
                    "EmailCode"
                )
            })
        }
        composable("EmailCode") {
            EmailCodeScreen(onNavigateToRegistration = {
                navController.navigate(
                    "Registration"
                )
            }, onNavigateToPassword = { navController.navigate("Password") })
        }
        composable("Password") { PasswordScreen(onNavigateToMain = { navController.navigate("Main") }, onNavigateToPatientCard = {navController.navigate("PatientCard")}) }
        composable("PatientCard") { PatientCardScreen(onNavigateToMain = { navController.navigate("Main") }) }
        composable("Main") { MainScreen() }
    }

}

@Preview(showBackground = true)
@Composable
fun ScreenPreview() {
    MedicTheme {

    }
}