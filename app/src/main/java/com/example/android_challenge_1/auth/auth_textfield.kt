package com.example.android_challenge_1.auth

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import com.example.android_challenge_1.components.OutLineTextField

@Composable
fun AuthTextField(
    label: String,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit,
    text: String,
){
    OutLineTextField(
        label = label,
        text = text,
        onValueChange = onValueChange,
        shape = CircleShape,
        isPassword = isPassword,
        maxLines = 1
    )
}