package com.example.android_challenge_1.components


import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp

@Composable
fun NoBackgroundTextButton(text: String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Black,
        )
    ) {
        Text(
            text,
            style = TextStyle(
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                textDecoration = TextDecoration.Underline,
                color = Color.White,
            ),
        )

    }
}