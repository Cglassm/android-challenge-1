package com.example.android_challenge_1.note

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.android_challenge_1.ui.theme.Androidchallenge1Theme
import com.example.android_challenge_1.utils.editNote
import com.example.android_challenge_1.utils.getNoteById
import com.example.android_challenge_1.utils.saveNewNote
import kotlinx.coroutines.launch
import java.util.Calendar

@Composable
fun CreateNotescreen(onSave: (String) -> Unit, noteId: Int) {
    val context = LocalContext.current
    val noteTitleState = remember { mutableStateOf("") }
    val noteBodyState = remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val modifier = Modifier
        .fillMaxWidth()
        .padding(32.dp)
    val noteItemsState = remember { mutableStateOf(listOf<String>()) }



    val canBeSaved =
        noteTitleState.value.isNotEmpty() || noteBodyState.value.isNotEmpty() || noteItemsState.value.isNotEmpty()

    Scaffold(
        containerColor = Color.White,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        content = { contentPadding ->

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(contentPadding)
                    .padding(32.dp)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LaunchedEffect(noteId) {
                    if (noteId > 0) {
                        val newNote = context.getNoteById(noteId)
                        if (newNote != null) {
                            noteTitleState.value = newNote.title
                            noteBodyState.value = newNote.contenido
                            noteItemsState.value = newNote.itemList
                        }
                    }
                }


                Text(
                    text =  if (noteId ==0) "Es momento de crear la nota!" else "Es momento de editar la nota!" , modifier = modifier, style = TextStyle(
                        color = Color(0xFF4B0082),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.W300,
                        textAlign = TextAlign.Center,
                    )
                )
                NoteTextfield(
                    "Título",
                    text = noteTitleState.value,
                    onValueChange = { newValue -> noteTitleState.value = newValue },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(100.dp),
                    maxLines = 3
                )
                NoteTextfield(
                    "Escriba aquí su nota...",
                    text = noteBodyState.value,
                    onValueChange = { newValue -> noteBodyState.value = newValue },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp),
                    maxLines = 50
                )
                NoteItemsList(
                    items = noteItemsState
                )
                Spacer(modifier = Modifier.height(16.dp))
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    CreateNoteButton(
                        onClick = {
                            if (canBeSaved) {
                                scope.launch {
                                    val calendar = Calendar.getInstance()
                                    val day = calendar.get(Calendar.DAY_OF_MONTH)
                                    val month = calendar.get(Calendar.MONTH) + 1
                                    val year = calendar.get(Calendar.YEAR)
                                    val prevNote = context.getNoteById(noteId)
                                    if (noteId > 0 && prevNote != null){
                                        context.editNote(
                                            noteId = noteId,
                                            newTitle = noteTitleState.value,
                                            newContent = noteBodyState.value,
                                            newDay = day,
                                            newMonth = month,
                                            newYear = year,
                                            newItems = noteItemsState.value,
                                        )
                                    } else {
                                        context.saveNewNote(
                                            noteTitle = noteTitleState.value,
                                            noteContent = noteBodyState.value,
                                            day = day,
                                            month = month,
                                            year = year,
                                            items = noteItemsState.value,
                                        )
                                    }
                                }
                                onSave("")
                            }
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd),
                        isEnabled = canBeSaved
                    )
                }
            }


        },
    )


}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPreview(modifier: Modifier = Modifier) {
    Androidchallenge1Theme {
        CreateNotescreen(onSave = {}, 0)
    }
}