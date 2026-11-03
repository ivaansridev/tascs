package dev.ivaansridev.tascs.storage

import java.io.File

private val storageFile: File by lazy {
    val dir = File(System.getProperty("user.home"), ".tascs")
    dir.mkdirs()
    File(dir, "tascs_data.json")
}

actual fun saveData(json: String) {
    storageFile.writeText(json)
}

actual fun loadData(): String? = if (storageFile.exists()) storageFile.readText() else null

actual fun exportData(): String = loadData() ?: "{}"

actual fun importData(json: String) = saveData(json)
