package com.example.android_challenge_1.auth

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_challenge_1.components.CustomTextButton
import com.example.android_challenge_1.ui.theme.Androidchallenge1Theme
import com.example.android_challenge_1.utils.isValidEmail
import androidx.compose.ui.platform.LocalContext
import com.example.android_challenge_1.utils.isValidPassword
import com.example.android_challenge_1.utils.saveCredentials
import kotlinx.coroutines.launch

@Composable
fun SignUpScreen(onSuccess: (String) -> Unit) {
    val context = LocalContext.current
    val emailState = remember { mutableStateOf("") }
    val passwordState = remember { mutableStateOf("") }
    val repeatPasswordState = remember { mutableStateOf("") }
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
                            text = "Bienvenido a\n NOTE APP",
                            modifier = modifier,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W500,
                                textAlign = TextAlign.Center,
                                fontFamily = FontFamily.Serif,
                            )
                        )
                        Text(
                            text = "Solicitamos que te registres con mail y contraseña y asi las notas solo las podrás ver tu",
                            modifier = modifier,
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W300,
                                textAlign = TextAlign.Center,
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
                AuthTextField(
                    "Repetir Contraseña",
                    text = repeatPasswordState.value,
                    onValueChange = { newValue -> repeatPasswordState.value = newValue },
                    isPassword = true,
                )
                Spacer(modifier = Modifier.height(56.dp))
                CustomTextButton(
                    Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    text = "Registrar",
                    color = Color.Black.copy(alpha = 0.5f),
                    textColor = Color.White,
                    onClick = {
                        val email = emailState.value
                        val password = passwordState.value
                        val repeatPassword = repeatPasswordState.value

                        if (!isValidEmail(email)) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Email no válido",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        } else if(!isValidPassword(password)) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "La contraseña debe tener al menos 6 caracteres, una mayúscula y una minúscula",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        } else if (password != repeatPassword) {
                            scope.launch {
                                snackbarHostState.showSnackbar(
                                    message = "Las contraseñas no coinciden",
                                    duration = SnackbarDuration.Short
                                )
                            }
                        } else {
                            scope.launch {
                                context.saveCredentials(email, password)
                                onSuccess(email)
                            }
                        }

                    },

                    )
            }
        },
    )


}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview(modifier: Modifier = Modifier) {
    Androidchallenge1Theme {
        SignUpScreen(onSuccess = {})
    }
}