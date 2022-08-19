plugins {
    val gradleVersion = "7.4.0-alpha09"

    id(Plugins.Android.application) version gradleVersion apply false
    id(Plugins.Android.library) version gradleVersion apply false
    id(Plugins.Kotlin.android) version Dependency.Kotlin.version apply false
    id(Plugins.Kotlin.multiplatform) version Dependency.Kotlin.version apply false
    id(Plugins.Kotlin.serialization) version Dependency.Kotlin.version apply false
    id(Plugins.aboutLibraries) version Dependency.AboutLibraries.version apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
