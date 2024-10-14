package com.example.android_challenge_1.account

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_challenge_1.auth.LoginScreen
import com.example.android_challenge_1.ui.theme.Androidchallenge1Theme
import com.example.android_challenge_1.utils.clearAllData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountScreen(onLogOutPressed: () -> Unit, onDeleteAccountPressed: () -> Unit) {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    var deleteDialogShown by remember { mutableStateOf(false) }
    var logOutDialogShown by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            Text(text = "Cuenta", fontSize = 24.sp, color = Color(0xFF4B0082))

            Spacer(modifier = Modifier.height(24.dp))

            AccountListItem(
                text = "Eliminar Cuenta",
                icon = { Icon(Icons.Default.Delete, contentDescription = "Delete Icon") },
                modifier = Modifier.clickable {
                    deleteDialogShown = true
                },

            )
            Spacer(modifier = Modifier.height(18.dp))
            AccountListItem(
                text = "Cerrar sesión",
                modifier = Modifier.clickable {
                    logOutDialogShown = true
                },
                icon = { Icon(Icons.Default.ExitToApp, contentDescription = "Logout Icon") },

            )
        }

        if (deleteDialogShown) {
            AccountAlertDialog(
                onDismissRequest = { deleteDialogShown = false },
                title = "Confirmar",
                text = "¿Estás seguro de que deseas eliminar tu cuenta? Esta acción eliminará tus notas y datos y no se puede deshacer.",
                confirmButtonPressed = {
                    coroutineScope.launch {
                        context.clearAllData()
                        onDeleteAccountPressed()
                    }
                    deleteDialogShown = false
                },
                cancelButtonPressed = { deleteDialogShown = false }
            )
        }

        if (logOutDialogShown) {
            AccountAlertDialog(
                onDismissRequest = { logOutDialogShown = false },
                title = "Confirmar",
                text = "¿Estás seguro de que deseas cerrar sesión?",
                confirmButtonPressed = {
                    onLogOutPressed()
                    logOutDialogShown = false
                },
                cancelButtonPressed = { logOutDialogShown = false }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(modifier: Modifier = Modifier) {
    Androidchallenge1Theme {
        AccountScreen(onLogOutPressed = {}, onDeleteAccountPressed = {})
    }
}
