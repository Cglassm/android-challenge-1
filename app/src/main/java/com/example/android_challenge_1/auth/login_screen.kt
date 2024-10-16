package com.example.android_challenge_1.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_challenge_1.components.CustomTextButton
import com.example.android_challenge_1.components.NoBackgroundTextButton
import com.example.android_challenge_1.ui.theme.Androidchallenge1Theme
import com.example.android_challenge_1.utils.getEmail
import com.example.android_challenge_1.utils.getPassword
import kotlinx.coroutines.launch

@Composable
fun LoginScreen(onLoginSuccess: (String) -> Unit, onRegisterPressed: () -> Unit) {
    val context = LocalContext.current
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)

    Scaffold(
        containerColor = Color(0xFFD4A5FF),
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Box(
                    modifier = modifier
                        .clip(RoundedCornerShape(16.dp))
                        .border(2.dp, Color.Gray, RoundedCornerShape(16.dp)),
                ) {
                    Column() {
                        Text(
                            text = "Hola!!!",
                            modifier = modifier,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.W300,
                                textAlign = TextAlign.Center,
                            )
                        )
                        Text(
                            text = "Bienvenido denuevo, te extrañamos por aca :)",
                            modifier = modifier,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif,
                            )
                        )
                    }
                }
                AuthTextField(
                    "Email",
                    text = emailState.value,
                    onValueChange = { newValue -> emailState.value = newValue },
                )
                AuthTextField(
                    "Contraseña",
                    text = passwordState.value,
                    onValueChange = { newValue -> passwordState.value = newValue },
                    isPassword = true,
                )
                Spacer(modifier = Modifier.height(56.dp))
                CustomTextButton(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    text = "Ingresar",
                    color = Color.Black.copy(alpha = 0.5f),
                    textColor = Color.White,
                    onClick = {
                        val email = emailState.value
                        val password = passwordState.value
                        scope.launch {
                            val storedEmail = context.getEmail()
                            val storedPassword = context.getPassword()
                            if (email == storedEmail && password == storedPassword) {
                                onLoginSuccess(email)
                            } else {
                                snackbarHostState.showSnackbar(
                                    message = "Credenciales incorrectas.\nIntentalo nuevamente",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        }


                    },

                    )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "No tenes cuenta aún?",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.W500,
                            textAlign = TextAlign.Center,
                        ),
                    )
                    NoBackgroundTextButton(
                        text = "Registrame",
                        onClick = {onRegisterPressed()},

                        )
                }
            }
        },
    )


}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(modifier: Modifier = Modifier) {
    Androidchallenge1Theme {
        LoginScreen(onLoginSuccess = {}, onRegisterPressed = {})
    }
}