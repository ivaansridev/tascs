package dev.ivaansridev.tascs.storage

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

/** Platform hooks implemented in androidMain and desktopMain. */
expect fun saveData(json: String)

expect fun loadData(): String?

expect fun exportData(): String

expect fun importData(json: String)

@Serializable
data class AppData(
    val tasks: List<dev.ivaansridev.tascs.model.Task> = emptyList(),
    val nextTaskId: Int = 1,
)

private val jsonFormat =
    Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }

/** Serializes and persists the whole app state. */
suspend fun saveAppData(data: AppData) {
    val json = jsonFormat.encodeToString(AppData.serializer(), data)
    withContext(Dispatchers.IO) { saveData(json) }
}

/** Loads persisted state, or null when nothing has been saved yet. */
suspend fun loadAppData(): AppData? {
    val raw = withContext(Dispatchers.IO) { loadData() } ?: return null
    return runCatching { jsonFormat.decodeFromString(AppData.serializer(), raw) }.getOrNull()
}

/** Parses a user supplied backup, returning null when it is not valid. */
fun decodeAppData(raw: String): AppData? = runCatching { jsonFormat.decodeFromString(AppData.serializer(), raw) }.getOrNull()

/** Encodes app state as pretty JSON for backup. */
fun encodeAppData(data: AppData): String = jsonFormat.encodeToString(AppData.serializer(), data)

/** Returns the current persisted state as pretty JSON for backup. */
suspend fun exportAppData(): String = withContext(Dispatchers.IO) { exportData() }

/** Replaces persisted state with a backup. */
suspend fun importAppData(json: String) = withContext(Dispatchers.IO) { importData(json) }
