plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.jetbrains.kotlin.serialization)
    alias(libs.plugins.kotlin.kapt)
}

android {
    namespace = "dev.lkey.financility"
    compileSdk = 35

    defaultConfig {
        applicationId = "dev.lkey.financility"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.52"

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
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.core.ktx)

    implementation(libs.jetbrains.compose.navigation)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // worker
    implementation(libs.androidx.work.runtime.ktx)

    // DI
    implementation(libs.dagger)
    kapt(libs.dagger.compiler)

    // serialization
    implementation(libs.kotlinx.serialization.json)
    implementation(project(":feature_articles"))

    // modules
    implementation(project(":feature_bill"))
    implementation(project(":feature_transactions"))
    implementation(project(":feature_settings"))
    implementation(project(":feature_splash"))

    // db
    implementation(project(":storage"))

    implementation(project(":core"))

    // common-components
    implementation(project(":common"))

    // to get articles
    implementation(project(":articles"))

    // to get account
    implementation(project(":account"))

}
