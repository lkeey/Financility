pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Financility"
include(":app")
include(":feature_articles")
include(":core")
include(":common")
include(":feature_settings")
include(":feature_bill")
include(":feature_transactions")
include(":storage")
include(":articles")
include(":account")
include(":feature_splash")
include(":transactions")
