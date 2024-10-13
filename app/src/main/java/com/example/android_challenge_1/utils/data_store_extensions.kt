package com.example.android_challenge_1.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.android_challenge_1.models.Note
import com.google.gson.Gson
import kotlinx.coroutines.flow.first
import Usernote.ListNote
import Usernote.UserNote
import Usernote.Date

const val DATA_STORE_FILE_NAME = "user_prefs.pb"

val Context.protoDataStore: DataStore<ListNote> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = ListNoteSerializer
)

suspend fun Context.saveNewNote(noteTitle: String, noteContent: String, day: Int, month: Int, year: Int, items: List<String>) {
    val newNote = protoDataStore.updateData {
            userNotes ->
            // Crear la nueva nota con los campos proporcionados
            val newNote = UserNote.newBuilder()
                .setTitle(noteTitle)
                .setContenido(noteContent)
                .setFecha(
                    Date.newBuilder()
                        .setDay(day)
                        .setMonth(month)
                        .setYear(year)
                        .build()
                )
                .addAllItem(items) // Agregar todos los items de la lista
                .build()

            // Agregar la nueva nota a la lista de notas existente
            userNotes.toBuilder()
                .addNotes(newNote) // Suponiendo que 'notes' es una lista en tu proto
                .build()




//            it.toBuilder()
//                .addNotes(
//                    UserNote.newBuilder()
//                        .setTitle(noteTitle)
//                        .setContenido(noteContent)
//                        .setFecha( Date.newBuilder()
//                            .setDay(day)
//                            .setMonth(month)
//                            .setYear(year)
//                            .build())
//                        .addItem(items[0])
//                        .build()
//                )
//                .build()
        }
}

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "prefs")

private val gson = Gson()

val EMAIL_KEY = stringPreferencesKey("user_email")
val PASSWORD_KEY = stringPreferencesKey("user_password")
val NOTES_LIST = stringPreferencesKey("notes_key")

suspend fun Context.saveCredentials(email: String, password: String) {
    dataStore.edit { preferences ->
        preferences[EMAIL_KEY] = email
        preferences[PASSWORD_KEY] = password
    }
}

suspend fun Context.getEmail(): String? {
    val preferences = dataStore.data.first()
    return preferences[EMAIL_KEY]
}

suspend fun Context.getPassword(): String? {
    val preferences = dataStore.data.first()
    return preferences[PASSWORD_KEY]
}

suspend fun Context.hasStoredCredentials(): Boolean {
    val email = getEmail()
    val password = getPassword()
    return !email.isNullOrEmpty() && !password.isNullOrEmpty()
}

suspend fun Context.clearPreferences() {
    dataStore.edit { preferences ->
        preferences.clear()
    }
}

suspend fun Context.saveNotes(notes: List<Note>) {
    val notesJson = gson.toJson(notes)
    dataStore.edit { preferences  ->
        preferences[NOTES_LIST] = notesJson
    }
}




