# Multiplatform Paging
Add the dependency in your common module's commonMain sourceSet

```kotlin
kotlin {
    // ...
    sourceSets {
        val commonMain by getting {
            dependencies {
                api("io.github.qdsfdhvh:paging:$last_version")
            }
        }
    }
}

```
## Setup


