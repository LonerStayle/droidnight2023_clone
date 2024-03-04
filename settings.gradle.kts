pluginManagement {
    includeBuild("build-logic")
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "CloneDroidNight2023"
include(":app")
include(":core")
include(":core:data")
include(":core:datastore")
include(":core:designsystem")
include(":core:domain")
include(":core:model")
include(":core:navigation")
include(":core:testing")
include(":core:ui")
include(":widget")
include(":feature")
include(":feature:main")
include(":feature:setting")
include(":feature:home")
include(":feature:bookmark")
include(":feature:contributor")
include(":feature:session")
