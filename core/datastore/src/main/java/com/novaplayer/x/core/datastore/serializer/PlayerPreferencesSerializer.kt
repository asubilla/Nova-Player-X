package com.novaplayer.x.core.datastore.serializer

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import com.novaplayer.x.core.model.PlayerPreferences
import java.io.InputStream
import java.io.OutputStream
import kotlinx.serialization.json.Json
import kotlinx.serialization.SerializationException

object PlayerPreferencesSerializer : Serializer<PlayerPreferences> {

    private val jsonFormat = Json { ignoreUnknownKeys = true }

    override val defaultValue: PlayerPreferences
        get() = PlayerPreferences()

    override suspend fun readFrom(input: InputStream): PlayerPreferences {
        try {
            return jsonFormat.decodeFromString(
                deserializer = PlayerPreferences.serializer(),
                string = input.readBytes().decodeToString(),
            )
        } catch (exception: SerializationException) {
            throw CorruptionException(
                message = "Cannot read datastore",
                cause = exception,
            )
        }
    }

    @Suppress("BlockingMethodInNonBlockingContext")
    override suspend fun writeTo(t: PlayerPreferences, output: OutputStream) {
        output.write(
            jsonFormat.encodeToString(
                serializer = PlayerPreferences.serializer(),
                value = t,
            ).encodeToByteArray(),
        )
    }
}
