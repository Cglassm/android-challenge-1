package com.example.android_challenge_1.account

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun AccountAlertDialog(
    onDismissRequest: ()->Unit,
    title: String,
    text: String,
    confirmButtonPressed: ()->Unit,
    cancelButtonPressed:()-> Unit,
){
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text(text = title) },
        text = { Text(text) },
        confirmButton = {
            TextButton(onClick = confirmButtonPressed) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = cancelButtonPressed) {
                Text("Cancelar")
            }
        }
    )
}