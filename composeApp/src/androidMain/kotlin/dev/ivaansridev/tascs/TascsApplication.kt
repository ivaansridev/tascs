package dev.ivaansridev.tascs

import android.app.Application
import dev.ivaansridev.tascs.storage.appContext

class TascsApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        appContext = applicationContext
    }
}
