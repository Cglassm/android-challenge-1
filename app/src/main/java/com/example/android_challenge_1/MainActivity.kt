package com.example.android_challenge_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.android_challenge_1.navigation.OurNavHost
import com.example.android_challenge_1.ui.theme.Androidchallenge1Theme
import com.example.android_challenge_1.utils.hasStoredCredentials

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val context = LocalContext.current
            val startDestination = remember { mutableStateOf("SignUpScreen") }
            LaunchedEffect(Unit) {
                val hasCredentials = context.hasStoredCredentials()
                startDestination.value = if (hasCredentials) "LoginScreen" else "SignUpScreen"
            }
            Androidchallenge1Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    OurNavHost(
                        navController = navController,
                        startDestination = startDestination.value

                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello Again! ${name}",
        modifier = modifier,
        style = TextStyle(
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.W300,
        )
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Androidchallenge1Theme {
        Greeting("Android")
    }
}