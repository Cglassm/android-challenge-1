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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.android_challenge_1.models.Note
import java.text.SimpleDateFormat
import java.util.Date

@Composable
fun Home(listNota : List<Note>) {
    Box (
        Modifier
            .background(Color.White)
            .padding(top = 60.dp)
    ){
        LazyColumn (
            modifier = Modifier.fillMaxSize()
        ) {
            items(listNota) {
                NoteCard(it)
            }
        }
    }

}

@Composable
fun NoteCard(note : Note) {
    Card (
        shape = RoundedCornerShape( 12.dp ),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        elevation = CardDefaults.cardElevation(6.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Column {
            Text(
                note.titulo,
                modifier = Modifier.padding(6.dp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                note.contenido,
                modifier = Modifier.padding(6.dp),
            )
            if (note.listaItems.isNotEmpty()) {
                note.listaItems.forEach {
                    Text(
                        text = "- " + it,
                        modifier = Modifier.padding( 6.dp)
                    )
                }
            }
            Text(
                text = SimpleDateFormat("dd/MM/YYYY").format(note.fecha),
                fontSize = 12.sp,
                color = Color.Gray,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(end = 8.dp, bottom = 8.dp)
            )
        }
    }
}

@Preview
@Composable
fun NotePreview() {
    val nota = Note( "Nota 1", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf())
    NoteCard( nota )
}



@Preview
@Composable
fun HomePreview() {
    val notes = listOf(
        Note("How To Draw A Professional Wireframe?", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf()),
        Note("Ways To Succeed Early", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf("Item 1", "Item 2", "Item 3")),
        Note("Scientific Facts Of Space", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf()),
        Note("Ways To Succeed Early", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf("Item 1", "Item 2", "Item 3")),
        Note("Scientific Facts Of Space", "Esta es una nota de prueba para la preview, ajja, añosdfja., no se wque poner aca pero tiene que ser largo para ver como queda la card xd.", Date(), listOf()),
    )
    val navController = rememberNavController()
    Home(notes)
}