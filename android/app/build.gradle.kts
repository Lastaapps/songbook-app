plugins {
    id(Plugins.Android.application)
    id(Plugins.Kotlin.android)
    id(Plugins.aboutLibraries)
}

project.group = App.GROUP

@Suppress("UnstableApiUsage")
android {
    namespace = "cz.lastaapps.songbook.app"

    if (App.USE_PREVIEW) {
        compileSdk = App.PREVIEW_COMPILE_SDK
        defaultConfig.targetSdk = App.PREVIEW_TARGET_SDK
    } else {
        compileSdk = App.COMPILE_SDK
        defaultConfig.targetSdk = App.TARGET_SDK
    }

    defaultConfig {
        applicationId = App.APP_ID

        //have to be specified explicitly for FDroid to work
        versionCode = 1000000 // 1x major . 2x minor . 2x path . 2x build diff
        versionName = "1.0.0"
        require(versionCode == App.VERSION_CODE)
        require(versionName == App.VERSION_NAME)

        minSdk = App.MIN_SDK

        resourceConfigurations.addAll(setOf("en", "cs"))

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        multiDexEnabled = true
    }
    buildTypes {
        getByName("debug") {
            applicationIdSuffix = ".debug"

            extra.set("alwaysUpdateBuildId", false)

            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
        }
    }
    packagingOptions {
        resources.excludes.add("META-INF/**")
        resources.excludes.add("mozilla/**")
    }
    compileOptions {
        isCoreLibraryDesugaringEnabled = true

        sourceCompatibility = Dependency.Java.version
        targetCompatibility = Dependency.Java.version
    }
    kotlinOptions {
        jvmTarget = Dependency.Java.jvmTarget
        freeCompilerArgs = listOf(
            "-Xjvm-default=all-compatibility",
            "-opt-in=kotlin.RequiresOptIn",
            "-Xbackend-threads=4",
        )
        languageVersion = Dependency.Kotlin.languageVersion
        apiVersion = Dependency.Kotlin.languageVersion
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dependency.Compose.compilerVersion
    }
}

dependencies {
    coreLibraryDesugaring(Dependency.desugaring)

    implementation(Dependency.AndroidX.core)
    implementation(Dependency.AndroidX.appcompat)
    implementation(Dependency.AndroidX.splashscreen)
    implementation(Dependency.AndroidX.vectorDrawables)
    implementation(Dependency.Google.material)

    implementation(Dependency.Compose.foundation)
    implementation(Dependency.Compose.material3)
    implementation(Dependency.Compose.material3WindowSizeClass)
    implementation(Dependency.Compose.animation)
    implementation(Dependency.Compose.iconsCore)
    implementation(Dependency.Compose.iconsExtended)
    implementation(Dependency.Compose.ui)
    implementation(Dependency.Compose.tooling)
    implementation(Dependency.Accompanist.drawablePainters)
    implementation(Dependency.Accompanist.flowLayouts)
    implementation(Dependency.Accompanist.navigationAnimation)
    implementation(Dependency.Accompanist.navigationMaterial)
    implementation(Dependency.Accompanist.pager)
    implementation(Dependency.Accompanist.permission)
    implementation(Dependency.Accompanist.swipeToRefresh)
    implementation(Dependency.Accompanist.systemUi)
}
