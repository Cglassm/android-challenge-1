package com.example.android_challenge_1.models

import java.util.Date

data class Note(
    val titulo : String,
    val contenido : String,
    val fecha : Date,
    val listaItems: List<String>
)
