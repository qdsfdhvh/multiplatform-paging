import java.util.Properties

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("maven-publish")
    id("signing")
}

group = "io.github.qdsfdhvh"
version = "1.0.1"

val COROUTINES_VERSION: String by rootProject.extra

kotlin {
    jvm()
    ios()
    iosSimulatorArm64()

    val commonMain by sourceSets.getting {
        dependencies {
            implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$COROUTINES_VERSION")
        }
    }

    val jvmMain by sourceSets.getting
    val iosMain by sourceSets.getting
    val iosTest by sourceSets.getting
    val iosSimulatorArm64Main by sourceSets.getting
    val iosSimulatorArm64Test by sourceSets.getting

    // Set up dependencies between the source sets
    iosSimulatorArm64Main.dependsOn(iosMain)
    iosSimulatorArm64Test.dependsOn(iosTest)
}

ext {
    val publishPropFile = rootProject.file("publish.properties")
    if (publishPropFile.exists()) {
        Properties().apply {
            load(publishPropFile.inputStream())
        }.forEach { name, value ->
            set(name.toString(), value)
        }
    } else {
        set("signing.keyId", System.getenv("SIGNING_KEY_ID"))
        set("signing.password", System.getenv("SIGNING_PASSWORD"))
        set("signing.secretKeyRingFile", System.getenv("SIGNING_SECRET_KEY_RING_FILE"))
        set("ossrhUsername", System.getenv("OSSRH_USERNAME"))
        set("ossrhPassword", System.getenv("OSSRH_PASSWORD"))
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

publishing {
    signing {
        sign(publishing.publications)
    }
    repositories {
        maven {
            val releasesRepoUrl = "https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/"
            val snapshotsRepoUrl = "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            url = if (version.toString().endsWith("SNAPSHOT")) {
                uri(snapshotsRepoUrl)
            } else {
                uri(releasesRepoUrl)
            }
            credentials {
                username = project.ext.get("ossrhUsername").toString()
                password = project.ext.get("ossrhPassword").toString()
            }
        }
    }
    publications.withType<MavenPublication> {
        artifact(javadocJar.get())
        pom {
            name.set("Multiplatform Paging")
            description.set("Jetpack Paging3 for Kotlin Multiplatform.")
            url.set("https://github.com/qdsfdhvh/multiplatform-paging")

            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            developers {
                developer {
                    id.set("Seiko")
                    name.set("SeikoDes")
                    email.set("seiko_des@outlook.com")
                }
            }
            scm {
                url.set("https://github.com/qdsfdhvh/multiplatform-paging")
                connection.set("scm:git:git://github.com/qdsfdhvh/multiplatform-paging.git")
                developerConnection.set("scm:git:git://github.com/qdsfdhvh/multiplatform-paging.git")
            }
        }
    }
}