package com.example.android_challenge_1.note

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.android_challenge_1.components.CustomTextButton

@Composable
fun CreateNoteButton(onClick: () -> Unit, modifier: Modifier, isEnabled: Boolean) {
    CustomTextButton(
        modifier,
        text = "Save",
        color = if (isEnabled) Color(0xFF9C27B0) else Color(0xFFD4A5FF),
        textColor = Color.White,
        onClick = onClick,
        shape = RoundedCornerShape(20.dp)
    )
}