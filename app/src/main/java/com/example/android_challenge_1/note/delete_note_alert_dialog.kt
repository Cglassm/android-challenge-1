package com.example.android_challenge_1.note

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DeleteNoteAlert(
    onDismissRequest: () -> Unit,
    onConfirmButtonPressed: () -> Unit,
    onDismissButtonPressed: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = onDismissRequest,
        title = { Text("Confirmar eliminación") },
        text = { Text("¿Estás seguro de que deseas eliminar esta nota? Esta acción no se puede deshacer.") },
        confirmButton = {
            TextButton(
                onClick = onConfirmButtonPressed
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismissButtonPressed) {
                Text("Cancelar")
            }
        }
    )
}