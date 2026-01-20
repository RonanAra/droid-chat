plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.composeCompiler)
    alias(libs.plugins.kotlinKsp)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "br.com.droidchat"
    compileSdk = 36

    defaultConfig {
        applicationId = "br.com.droidchat"
        minSdk = 26
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {

    implementation(platform(libs.androidx.compose.bom))
    implementation(platform(libs.firebase.bom))
    ksp(libs.hilt.compiler)
    implementation(libs.bundles.android.core)
    implementation(libs.bundles.ui.compose)
    implementation(libs.bundles.navigation.di)
    implementation(libs.bundles.network)
    implementation(libs.bundles.data)
    implementation(libs.bundles.hilt)
    implementation(libs.bundles.firebase)
    implementation(libs.bundles.kotlin)
    implementation(libs.bundles.security)

    testImplementation(libs.bundles.test)
    androidTestImplementation(platform(libs.androidx.compose.bom))

}