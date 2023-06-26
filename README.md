# Multiplatform Paging

[![](https://img.shields.io/badge/Kotlin-Multiplatform-%237f52ff?logo=kotlin)](https://kotlinlang.org/docs/multiplatform.html)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.qdsfdhvh/paging/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.qdsfdhvh/paging)
[![](https://img.shields.io/github/license/qdsfdhvh/multiplatform-paging)](https://github.com/qdsfdhvh/multiplatform-paging/blob/main/LICENSE)

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


