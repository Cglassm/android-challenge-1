package com.example.android_challenge_1.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val email = "Example"
    BottomNavigation(backgroundColor = Color(0xFFD4A5FF), contentColor = Color.White) {
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = navController.currentDestination?.route?.startsWith("HomeScreen") == true,
            selectedContentColor = Color(0xFF6200EE),
            unselectedContentColor = Color.White,
            onClick = {
                navController.navigate("HomeScreen/$email") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
        BottomNavigationItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Account") },
            label = { Text("Account") },
            selected = navController.currentDestination?.route == "AccountScreen",
            selectedContentColor = Color(0xFF6200EE),
            unselectedContentColor = Color.White,
            onClick = {
                navController.navigate("AccountScreen") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        )
    }
}
