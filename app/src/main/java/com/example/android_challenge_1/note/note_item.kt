package com.example.android_challenge_1.note

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.android_challenge_1.components.OutLineTextField
import com.example.android_challenge_1.ui.theme.Androidchallenge1Theme

@Composable
fun NoteItem(
    text: String,
    onTextChange: (String) -> Unit,
    onDelete: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutLineTextField(
            text = text,
            onValueChange = onTextChange,
            label = "Escriba aqui",
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.weight(1f),
            maxLines = 4
            )
        IconButton(
            onClick = onDelete,
        ) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Eliminar",
                tint = Color(0xFF6A1B9A)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(modifier: Modifier = Modifier) {
    Androidchallenge1Theme {
        NoteItem(
            text = "Hey",
            onTextChange = {
            },
            onDelete = {

            }
        )
    }
}