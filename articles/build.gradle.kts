plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "dev.lkey.articles"
    compileSdk = 35

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    // testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // ui
    implementation(libs.androidx.material3)

    // network
//    implementation(libs.ktor.client.core)
//    implementation(libs.ktor.client.negotiation)
//    implementation(libs.ktor.json)
//    implementation(libs.ktor.client.logging)
    implementation(libs.ktor.client.okhttp)

    // Dagger 2
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // ktor-client
    implementation(project(":core"))
}