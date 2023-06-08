import org.gradle.api.JavaVersion

object Versions {

    object Android {
        const val min = 21
        const val compile = 33
    }

    object Kotlin {
        const val lang = "1.8.20"
        const val coroutines = "1.7.1"
        // const val serialization = "1.3.3"
    }

    object Java {
        const val jvmTarget = "11"
        val java = JavaVersion.VERSION_11
    }

    const val spotless = "6.19.0"
    const val ktlint = "0.48.2"
}