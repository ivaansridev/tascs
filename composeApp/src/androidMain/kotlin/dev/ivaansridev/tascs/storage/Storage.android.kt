package dev.ivaansridev.tascs.storage

import android.app.Application
import android.content.Context

// Application context holder – set once in Application.onCreate()
internal lateinit var appContext: Context

actual fun saveData(json: String) {
    appContext
        .getSharedPreferences("tascs_data", Context.MODE_PRIVATE)
        .edit()
        .putString("app_data_json", json)
        .apply()
}

actual fun loadData(): String? =
    appContext
        .getSharedPreferences("tascs_data", Context.MODE_PRIVATE)
        .getString("app_data_json", null)

actual fun exportData(): String = loadData() ?: "{}"

actual fun importData(json: String) = saveData(json)
