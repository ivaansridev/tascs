import java.io.FileInputStream
import java.util.Properties

val keystorePropertiesFile = rootProject.file("keystore.properties")
val keystoreProperties =
    Properties().apply {
        if (keystorePropertiesFile.exists()) {
            FileInputStream(keystorePropertiesFile).use { load(it) }
        }
    }

plugins {
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.android.application)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.compose.hot.reload)
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.kotlin.serialization)
}

kotlin {
    androidTarget()
    jvm("desktop")
    jvmToolchain(21)

    sourceSets {
        commonMain.dependencies {
            implementation(libs.compose.runtime)
            implementation(libs.compose.foundation)
            implementation(libs.compose.material3)
            implementation(libs.compose.material.icons.core)
            implementation(libs.compose.ui)
            implementation(libs.kotlinx.datetime)
            implementation(libs.confettikit)
            implementation(libs.kotlinx.serialization.core)
            implementation(libs.kotlinx.serialization.json)
        }
        androidMain.dependencies {
            implementation(libs.androidx.activity.compose)
        }
        val desktopMain by getting
        desktopMain.dependencies {
            implementation(compose.desktop.currentOs)
        }
    }
}

android {
    namespace = "dev.ivaansridev.tascs"
    compileSdk = 35
    defaultConfig {
        applicationId = "dev.ivaansridev.tascs"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "0.1.0"
    }
    signingConfigs {
        create("release") {
            if (keystorePropertiesFile.exists()) {
                storeFile = rootProject.file(keystoreProperties.getProperty("storeFile"))
                storePassword = keystoreProperties.getProperty("storePassword")
                keyAlias = keystoreProperties.getProperty("keyAlias")
                keyPassword = keystoreProperties.getProperty("keyPassword")
            }
        }
    }
    lint {
        // AGP 8.7.3's lint crashes on Kotlin 2.2 sources (NullSafeMutableLiveData
        // detector), which aborts `assembleRelease`. Skip the release lint gate;
        // run `./gradlew :composeApp:lintDebug` manually when needed.
        checkReleaseBuilds = false
        disable += "NullSafeMutableLiveData"
        disable += "FrequentlyChangingValue"
        disable += "RememberInComposition"
    }
    buildTypes {
        release {
            // Without this the APK is produced unsigned, which Android refuses to
            // install with a "Package invalid" / "App not installed" error.
            signingConfig =
                if (keystorePropertiesFile.exists()) {
                    signingConfigs.getByName("release")
                } else {
                    // Keep release builds installable even when the local keystore is
                    // absent (e.g. CI); replace with a real release key for distribution.
                    logger.warn("keystore.properties not found - signing release with the debug key")
                    signingConfigs.getByName("debug")
                }
        }
    }
}

compose.desktop {
    application {
        mainClass = "dev.ivaansridev.tascs.MainKt"
        nativeDistributions {
            packageName = "tascs"
            packageVersion = "0.1.0"
        }
    }
}
