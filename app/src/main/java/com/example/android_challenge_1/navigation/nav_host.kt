package com.example.android_challenge_1.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.android_challenge_1.account.AccountScreen
import com.example.android_challenge_1.auth.LoginScreen
import com.example.android_challenge_1.auth.SignUpScreen
import com.example.android_challenge_1.home.Home
import com.example.android_challenge_1.models.Note
import java.util.Date
import com.example.android_challenge_1.note.CreateNotescreen

@Composable
fun OurNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String
) {
    val isAuthenticated = remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            if (isAuthenticated.value) {
                BottomNavigationBar(navController = navController)
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = startDestination,
            modifier = modifier.padding(innerPadding)
        ) {
            composable("LoginScreen") {
                LoginScreen(
                    onLoginSuccess = { email ->
                        isAuthenticated.value = true
                        navController.navigate("HomeScreen/$email?")
                    },
                    onRegisterPressed = {
                        navController.navigate("SignUpScreen")
                    }
                )
            }
            composable("SignUpScreen") {
                SignUpScreen(
                    onSuccess = { email ->
                        isAuthenticated.value = true
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
                Home(
                    onCreateNote = { navController.navigate("CreateNotescreen/0") },
                    onEditNote = { id ->
                        navController.navigate("CreateNotescreen/$id")
                    }
                )
            }
            composable(
                    route ="CreateNotescreen/{id}",
                    arguments = listOf(
                        navArgument("id") { type = NavType.IntType },
                    )
                ) { backStackEntry ->
                val id = backStackEntry.arguments?.getInt("id", 0) ?: 0
                CreateNotescreen(
                    onSave = {
                        navController.popBackStack()
                    },
                    noteId = id,
                )
            }
            composable("AccountScreen") {
                AccountScreen(
                    onDeleteAccountPressed ={
                        navController.navigate("SignUpScreen")
                        isAuthenticated.value = false
                    },
                    onLogOutPressed = {
                        navController.navigate("LoginScreen")
                        isAuthenticated.value = false
                    }

                )
            }
        }
    }
}
