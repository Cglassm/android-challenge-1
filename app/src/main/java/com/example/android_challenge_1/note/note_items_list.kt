package com.example.android_challenge_1.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.android_challenge_1.components.CustomTextButton

@Composable
fun NoteItemsList(items: MutableState<List<String>>) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items.value.forEachIndexed { index, item ->
            NoteItem(
                text = item,
                onTextChange = { newText ->
                    items.value = items.value.toMutableList().apply {
                        set(index, newText)
                    }
                },
                onDelete = {
                    items.value = items.value.toMutableList().apply { removeAt(index) }
                }
            )
        }
        CustomTextButton(
            onClick = {
                items.value += ""
            },
            text = "+",
            textColor =  Color.White,
            modifier = Modifier
                .align(Alignment.Start),
            color = Color(0xFF9C27B0)
        )
    }
}