package com.example.android_challenge_1.utils

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream
import Usernote.UserNote
import Usernote.ListNote
import Usernote.Date

object ListNoteSerializer: Serializer<ListNote> {

    override val defaultValue: ListNote = ListNote.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): ListNote {
        try {
            return ListNote.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: ListNote, output: OutputStream) = t.writeTo(output)

}

object UserNoteSerializer: Serializer<UserNote> {

    override val defaultValue: UserNote = UserNote.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): UserNote {
        try {
            return UserNote.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: UserNote, output: OutputStream) = t.writeTo(output)

}

object DateSerializer: Serializer<Date> {

    override val defaultValue: Date = Date.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): Date {
        try {
            return Date.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: Date, output: OutputStream) = t.writeTo(output)

}