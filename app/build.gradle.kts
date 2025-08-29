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

    // modules
    implementation(project(":feature:feature_articles"))
    implementation(project(":feature:feature_bill"))
    implementation(project(":feature:feature_transactions"))
    implementation(project(":feature:feature_settings"))
    implementation(project(":feature:feature_splash"))

    // db
    implementation(project(":datasource:storage"))

    implementation(project(":core"))

    // common-components
    implementation(project(":common"))

    // to get articles
    implementation(project(":datasource:articles"))

    // to get account
    implementation(project(":datasource:account"))

    // to get transactions
    implementation(project(":datasource:transactions"))

}
