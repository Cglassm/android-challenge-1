package com.example.android_challenge_1.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CustomTextButton(
    modifier: Modifier,
    onClick: () -> Unit,
    text: String,
    color: Color,
    textColor: Color,
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = color, contentColor = textColor),
    ) {
        Text(
            text = text, style = TextStyle(
                color = textColor,
                fontSize = 20.sp,
                fontWeight = FontWeight.W600,
                textAlign = TextAlign.Center,
            )
        )
    }
}

