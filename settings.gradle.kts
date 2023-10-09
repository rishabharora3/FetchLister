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

rootProject.name = "FetchLister"
include(":app")
include(":core")
include(":core:network")
include(":core:common")
include(":core:data")
include(":core:model")
include(":core:database")
include(":core:domain")
include(":feature")
include(":feature:fetchlist")
include(":core:ui")
include(":core:designsystem")
include(":feature:characterlist")
include(":feature:characterdetail")
