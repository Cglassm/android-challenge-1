package com.example.android_challenge_1.utils

import android.provider.ContactsContract.CommonDataKinds.Note
import com.google.gson.Gson

private val gson: Gson = Gson()

fun noteToJson(note: Note): String {
    return Gson().toJson(note)
}

fun noteFromJson(json: String): Note {
    return Gson().fromJson(json, Note::class.java)
}
