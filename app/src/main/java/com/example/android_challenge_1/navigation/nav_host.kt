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
import com.example.android_challenge_1.home.Home
import com.example.android_challenge_1.home.Note
import java.util.Date
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

            val notes = listOf(
                Note("How To Draw A Professional Wireframe?", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf()),
                Note("Ways To Succeed Early", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf("Item 1", "Item 2", "Item 3")),
                Note("Scientific Facts Of Space", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf()),
                Note("Ways To Succeed Early", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf("Item 1", "Item 2", "Item 3")),
                Note("Scientific Facts Of Space", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf()),
            )
            Home(listNota = notes)

        }
        composable("CreateNotescreen") {
            CreateNotescreen(
                onSave = {}
            )
        }
    }
}