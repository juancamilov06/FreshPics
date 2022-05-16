package com.juanvilla.freshpic.data.source.local.agecontrol

import androidx.datastore.core.Serializer
import com.juanvilla.freshpic.data.source.local.entities.ProtoAgeControlPreferences
import java.io.InputStream
import java.io.OutputStream
import kotlinx.serialization.SerializationException
import kotlinx.serialization.json.Json

object AgeControlSerializer : Serializer<ProtoAgeControlPreferences> {
    override val defaultValue: ProtoAgeControlPreferences
        get() = ProtoAgeControlPreferences()

    override suspend fun readFrom(input: InputStream): ProtoAgeControlPreferences =
        try {
            Json.decodeFromString(
                deserializer = ProtoAgeControlPreferences.serializer(),
                string = input.readBytes().decodeToString()
            )
        } catch (error: SerializationException) {
            error.printStackTrace()
            defaultValue
        }

    override suspend fun writeTo(t: ProtoAgeControlPreferences, output: OutputStream) {
        output.write(
            Json.encodeToString(
                ProtoAgeControlPreferences.serializer(), t
            ).encodeToByteArray()
        )
    }
}