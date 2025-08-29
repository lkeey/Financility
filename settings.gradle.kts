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

include(":feature:feature_articles")
include(":feature:feature_settings")
include(":feature:feature_bill")
include(":feature:feature_transactions")
include(":feature:feature_splash")

include(":datasource:storage")
include(":datasource:articles")
include(":datasource:account")
include(":datasource:transactions")

include(":core")
include(":common")
