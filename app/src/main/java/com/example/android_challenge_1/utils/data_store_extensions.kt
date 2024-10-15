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
import androidx.datastore.preferences.core.intPreferencesKey

const val DATA_STORE_FILE_NAME = "user_prefs.pb"

val Context.protoDataStore: DataStore<ListNote> by dataStore(
    fileName = DATA_STORE_FILE_NAME,
    serializer = ListNoteSerializer
)

private val LAST_NOTE_ID_KEY = intPreferencesKey("last_note_id")

suspend fun Context.getNextNoteId(): Int {
    val preferences = dataStore.data.first()
    val lastNoteId = preferences[LAST_NOTE_ID_KEY] ?: 0
    dataStore.edit { settings ->
        settings[LAST_NOTE_ID_KEY] = lastNoteId + 1
    }
    return lastNoteId + 1
}

suspend fun Context.saveNewNote(
    noteTitle: String,
    noteContent: String,
    day: Int,
    month: Int,
    year: Int,
    items: List<String>,
) {
    println(noteTitle)
    val newId = getNextNoteId()
    val newNote = protoDataStore.updateData { userNotes ->
        val newNote = UserNote.newBuilder()
            .setTitle(noteTitle)
            .setId(newId)
            .setContenido(noteContent)
            .setFecha(
                Date.newBuilder()
                    .setDay(day)
                    .setMonth(month)
                    .setYear(year)
                    .build()
            )
            .addAllItem(items)
            .build()

        userNotes.toBuilder()
            .addNotes(newNote)
            .build()

    }
}

suspend fun Context.deleteNote(noteId: Int) {
    protoDataStore.updateData { userNotes ->
        val updatedNotes = userNotes.notesList.filter { it.id != noteId }
        userNotes.toBuilder()
            .clearNotes()
            .addAllNotes(updatedNotes)
            .build()
    }
}

suspend fun Context.editNote(
    noteId: Int,
    newTitle: String? = null,
    newContent: String? = null,
    newDay: Int? = null,
    newMonth: Int? = null,
    newYear: Int? = null,
    newItems: List<String>? = null
) {
    protoDataStore.updateData { userNotes ->
        val updatedNotes = userNotes.notesList.map { note ->
            if (note.id == noteId) {
                note.toBuilder()
                    .apply {
                        newTitle?.let { setTitle(it) }
                        newContent?.let { setContenido(it) }
                        if (newDay != null && newMonth != null && newYear != null) {
                            setFecha(
                                Date.newBuilder()
                                    .setDay(newDay)
                                    .setMonth(newMonth)
                                    .setYear(newYear)
                                    .build()
                            )
                        }
                        newItems?.let { clearItem().addAllItem(it) }
                    }
                    .build()
            } else {
                note
            }
        }
        userNotes.toBuilder()
            .clearNotes()
            .addAllNotes(updatedNotes)
            .build()
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

suspend fun Context.clearAllData() {
    dataStore.edit { preferences ->
        preferences.clear()
    }
    protoDataStore.updateData { userNotes ->
        userNotes.toBuilder()
            .clearNotes()
            .build()
    }
}


suspend fun Context.saveNotes(notes: List<Note>) {
    val notesJson = gson.toJson(notes)
    dataStore.edit { preferences ->
        preferences[NOTES_LIST] = notesJson
    }
}




