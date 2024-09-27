package com.example.android_challenge_1.note

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.android_challenge_1.components.OutLineTextField

@Composable
fun NoteTextfield(
    label: String,
    onValueChange: (String) -> Unit,
    text: String,
    modifier: Modifier
){
    OutLineTextField(
        modifier = modifier,
        label,
        text = text,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(20.dp),
    )
}