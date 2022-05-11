import org.gradle.api.publish.maven.internal.artifact.FileBasedMavenArtifact
import java.net.URI
import java.io.FileInputStream
import java.util.Properties

plugins {
    id("org.jetbrains.kotlin.multiplatform")
    id("maven-publish")
    id("signing")
}

val MP_PAGING_VERSION: String by rootProject.extra

val sonatypePropertiesFile = project.rootProject.file("sonatype.properties")
val sonatypeProperties = Properties()
if (sonatypePropertiesFile.exists()) {
    sonatypeProperties.load(FileInputStream(sonatypePropertiesFile))

    rootProject.extra["signing.keyId"] = sonatypeProperties.getProperty("signing.key_id")
    rootProject.extra["signing.password"] = sonatypeProperties.getProperty("signing.password")
    rootProject.extra["signing.secretKeyRingFile"] = sonatypeProperties.getProperty("signing.secret_key_ring_file")
}

val artifactName = "multiplatform-paging"
val artifactGroup = "io.github.kuuuurt"
val artifactVersion = MP_PAGING_VERSION

val pomUrl = "https://github.com/kuuuurt/multiplatform-paging"
val pomScmUrl = "https://github.com/kuuuurt/multiplatform-paging.git"
val pomIssueUrl = "https://github.com/kuuuurt/multiplatform-paging/issues"
val pomDesc = "A Kotlin Multiplatform library for pagination on Android and iOS"

val githubRepo = "kuuuurt/multiplatform-paging"
val githubReadme = "README.md"

val pomLicenseName = "Apache-2.0"
val pomLicenseUrl = "https://www.apache.org/licenses/LICENSE-2.0"
val pomLicenseDist = "repo"

val pomDeveloperId = "kuuuurt"
val pomDeveloperName = "Kurt Renzo Acosta"
val pomDeveloperEmail = "kurt.r.acosta@gmail.com"

val frameworkName = "MultiplatformPaging"

group = artifactGroup
version = artifactVersion

val COROUTINES_VERSION: String by rootProject.extra

kotlin {
    ios()
    iosSimulatorArm64()

    jvm { compilations.all { kotlinOptions.jvmTarget = "11" } }

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

publishing {
    repositories {
        maven {
            name = "mavenCentral"
            url = URI("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = sonatypeProperties.getProperty("ossrh.username")
                password = sonatypeProperties.getProperty("ossrh.password")
            }
        }
    }
}

signing {
    if (sonatypePropertiesFile.exists()) {
        sign(publishing.publications)
    }
}

val javadocJar by tasks.registering(Jar::class) {
    archiveClassifier.set("javadoc")
}

afterEvaluate {
    project.publishing.publications.withType<MavenPublication>().all {
        groupId = artifactGroup
        artifactId = if (name.contains("kotlinMultiplatform")) {
            artifactName
        } else {
            "$artifactName-$name"
        }

        // Stub javadoc.jar artifact
        artifact(javadocJar.get())

        pom.withXml {
            asNode().apply {
                appendNode("description", pomDesc)
                appendNode("name", rootProject.name)
                appendNode("url", pomUrl)
                appendNode("licenses").appendNode("license").apply {
                    appendNode("name", pomLicenseName)
                    appendNode("url", pomLicenseUrl)
                    appendNode("distribution", pomLicenseDist)
                }
                appendNode("developers").appendNode("developer").apply {
                    appendNode("id", pomDeveloperId)
                    appendNode("name", pomDeveloperName)
                    appendNode("email", pomDeveloperName)
                }
                appendNode("scm").apply {
                    appendNode("url", pomScmUrl)
                }
            }
        }
    }
}
