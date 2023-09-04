# FetchLister
<p align="center">
  <img src="https://github.com/rishabharora3/FetchLister/assets/14349274/cc66c9af-6964-4d22-80cf-e52adecd4b12" alt="Image 1" width="300">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/rishabharora3/FetchLister/assets/14349274/6b1446cc-2a41-43ad-ba07-9aeabc9f661e" alt="Image 2" width="300">
</p>
<p align="center">
  <em>Dark Theme</em> &nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;<em>Light Theme</em>
</p>

FetchLister, powered by the latest Android technologies, is an ongoing sample project that follows Google's [nowinandroid](https://github.com/android/nowinandroid) architecture for efficient item listing. Leveraging the strength of Jetpack Compose, it offers a sleek and intuitive user experience. Built with Hilt for dependency injection, Retrofit2 & OkHttp3 for REST APIs, and Room for local database, FetchLister combines modern architecture with cutting-edge tools for seamless item management.

## Technology Stack

- Android Studio
- Android Platform
    - Minimum SDK level 24
    - Target SDK level 33
- Kotlin (no Java or Groovy!)
- [Jetpack Compose](https://developer.android.com/jetpack/compose)
    - [Navigation](https://developer.android.com/jetpack/compose/navigation) - to navigate between composables
    - [Compose Material](https://developer.android.com/jetpack/androidx/releases/compose-material) - to use Material Design Components
    - [Compose UI](https://developer.android.com/jetpack/androidx/releases/compose-ui) - to interact with the device, including layout, drawing, and input
    - [Compose Runtime](https://developer.android.com/jetpack/androidx/releases/compose-runtime) - Fundamental building blocks of Compose's programming model and state management
- `Activity`
- [StateFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) for sharing states
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs
- [Room](https://developer.android.com/jetpack/androidx/releases/room) for local database
- MVVM/Clean Architecture
- Github


## List Display Requirements

This list follows specific rules when displaying items to the user:

1. **Grouping by "listId":** The items in the list are grouped by their "listId" attribute. This means that items with the same "listId" are displayed together as a group.

2. **Sorting by "listId" and "name":** The results are sorted first by "listId" and then by "name" when displaying. This ensures that items within the same group (based on "listId") are ordered alphabetically by their "name" attribute.

3. **Filtering out blank or null names:** Any items where the "name" attribute is blank or null are filtered out before being displayed to the user. This ensures that only items with valid names are shown.

## Goals and requirements

The goals for the app architecture are:

*   Follow the [official architecture guidance](https://developer.android.com/jetpack/guide) as closely as possible.
*   Easy for developers to understand, nothing too experimental.
*   Support multiple developers working on the same codebase.
*   Minimize build times.


## Architecture overview

The app architecture has three layers: a [data layer](https://developer.android.com/jetpack/guide/data-layer), a [domain layer](https://developer.android.com/jetpack/guide/domain-layer) and a [UI layer](https://developer.android.com/jetpack/guide/ui-layer).

<p align="center">
  <img src="https://github.com/rishabharora3/FetchLister/assets/14349274/418dec28-b14f-4daa-841e-9a3a6e81545d" alt="Image 1" width="300">
</p>


The architecture follows a reactive programming model with [unidirectional data flow](https://developer.android.com/jetpack/guide/ui-layer#udf). With the data layer at the bottom, the key concepts are:

*   Higher layers react to changes in lower layers.
*   Events flow down.
*   Data flows up.

The data flow is achieved using streams, implemented using [Kotlin Flows](https://developer.android.com/kotlin/flow).

Read more here: 
* https://github.com/android/nowinandroid/blob/main/docs/ArchitectureLearningJourney.md
* https://github.com/android/nowinandroid/blob/main/docs/ModularizationLearningJourney.md

## Video Demo

https://github.com/rishabharora3/FetchLister/assets/14349274/7ffe39f4-10fe-49f4-9e73-ff62be25f5a1



