package com.example.android_challenge_1.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "prefs")

val EMAIL_KEY = stringPreferencesKey("user_email")
val PASSWORD_KEY = stringPreferencesKey("user_password")

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

