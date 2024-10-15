package com.example.android_challenge_1.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.android_challenge_1.models.Note
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import Usernote.UserNote
import Usernote.ListNote
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.IconButton
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.android_challenge_1.note.DeleteNoteAlert
import com.example.android_challenge_1.utils.deleteNote
import com.example.android_challenge_1.utils.protoDataStore
import kotlinx.coroutines.flow.Flow

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "CoroutineCreationDuringComposition")
@Composable
fun Home(onCreateNote: () -> Unit) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val userNotesFlow: Flow<ListNote> = context.protoDataStore.data
    val listaNotas = userNotesFlow.collectAsState(initial = ListNote.getDefaultInstance())
    var showDeleteNoteDialog by remember { mutableStateOf(false) }
    var noteToDelete by remember { mutableStateOf<UserNote?>(null) }
    val snackbarHostState = remember { SnackbarHostState() }

    if (showDeleteNoteDialog && noteToDelete != null) {
        DeleteNoteAlert(
            onDismissRequest = { showDeleteNoteDialog = false },
            onConfirmButtonPressed = {
                scope.launch {
                    context.deleteNote(noteId = noteToDelete!!.id)
                    snackbarHostState.showSnackbar(
                        "Se eliminó correctamente",
                        duration = SnackbarDuration.Short,
                    )
                }
                showDeleteNoteDialog = false
            },
            onDismissButtonPressed = { showDeleteNoteDialog = false }
        )
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onCreateNote() },
                modifier = Modifier.padding(10.dp),
                containerColor = Color.LightGray
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar nota",
                    tint = Color.Black
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        content = {
            Box(
                Modifier
                    .background(Color.White)
                    .padding(top = 10.dp)
            ) {
                if (listaNotas.value.notesList.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "¡Oh no! Aún no tienes ninguna nota 😲\n¡Comienza pulsando el botón + y crea tu primera nota!",
                            color = Color.Gray,
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .padding(32.dp)
                        )
                    }
                } else {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        items(listaNotas.value.notesList) { note ->
                            NoteCard(
                                note = note,
                                onDeleteNote = {
                                    noteToDelete = note
                                    showDeleteNoteDialog = true
                                },
                            )
                        }
                    }
                }
            }
        }
    )
}


@Preview
@Composable
fun NotePreview() {
    val nota = Note(
        "Nota 1",
        "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.",
        Date(),
        listOf()
    )
    //NoteCard( nota )
}


@Preview
@Composable
fun HomePreview() {
    val notes = listOf(
        Note(
            "How To Draw A Professional Wireframe?",
            "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.",
            Date(),
            listOf()
        ),
        Note(
            "Ways To Succeed Early",
            "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.",
            Date(),
            listOf("Item 1", "Item 2", "Item 3")
        ),
        Note(
            "Scientific Facts Of Space",
            "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.",
            Date(),
            listOf()
        ),
        Note(
            "Ways To Succeed Early",
            "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.",
            Date(),
            listOf("Item 1", "Item 2", "Item 3")
        ),
        Note(
            "Scientific Facts Of Space",
            "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.",
            Date(),
            listOf()
        ),
    )
    val navController = rememberNavController()
    Home({})
}