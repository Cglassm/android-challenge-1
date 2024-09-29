package com.example.android_challenge_1.account

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.ListItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.NavigateNext
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun AccountListItem(
    modifier: Modifier,
    text: String,
    icon: @Composable () -> Unit,
) {
    ListItem(
        text = { Text(text) },
        modifier = modifier
            .border(1.dp, Color(0xFF4B0082), shape = RoundedCornerShape(20.dp)),
        icon = icon,
        trailing = {
            Icon(
                Icons.AutoMirrored.Filled.NavigateNext,
                contentDescription = "Delete Account"
            )
        }
        )
}