package com.example.android_challenge_1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android_challenge_1.Greeting
import com.example.android_challenge_1.auth.LoginScreen
import com.example.android_challenge_1.auth.SignUpScreen
import com.example.android_challenge_1.note.CreateNotescreen

@Composable
fun OurNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    NavHost(navController, startDestination, modifier = modifier) {
        composable("LoginScreen") {
            LoginScreen(
                onLoginSuccess = { email ->
                    navController.navigate("HomeScreen/$email?")
                },
            )
        }
        composable("SignUpScreen") {
            SignUpScreen(
                onSuccess = { email ->
                    navController.navigate("HomeScreen/$email?")
                },
            )
        }
        composable(
            "HomeScreen/{email}?",
            arguments = listOf(
                navArgument("email") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val email = backStackEntry.arguments?.getString("email") ?: ""
            Greeting(
                name = email
            )
        }
        composable("CreateNotescreen") {
            CreateNotescreen(
                onSave = {}
            )
        }
    }
}