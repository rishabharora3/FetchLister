# FetchLister
<p align="center">
  <img src="https://github.com/rishabharora3/FetchLister/assets/14349274/39486224-5ec2-4a30-9372-bec0fd0621bc" alt="Image 1" width="300">
    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
  <img src="https://github.com/rishabharora3/FetchLister/assets/14349274/6ca8919b-2c86-4542-8110-53980af1730d" alt="Image 2" width="300">
</p>
<p align="center">
  <em>List Screen</em> &nbsp;&nbsp;&nbsp;| &nbsp;&nbsp;&nbsp;<em>Detail Screen</em>
</p>

FetchLister is a dynamic Android project that leverages the latest technologies for efficient item listing and detailed item information retrieval. This ongoing sample project adheres to Google's recommended [nowinandroid](https://github.com/android/nowinandroid) architecture. It harnesses the power of Jetpack Compose to deliver a sleek and user-friendly interface. The project is built with Hilt for dependency injection and Room for managing local database operations. FetchLister seamlessly blends modern architectural principles with cutting-edge tools, including Retrofit2 & OkHttp3 for GraphQL APIs, to provide a seamless item management experience.


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
    - [Jetpack Compose - Coil](https://coil-kt.github.io/coil/compose/) - Image loading library for Android backed by Kotlin Coroutines.
- `Activity`
- Coil - Image loading library for Android backed by Kotlin Coroutines.
- [StateFlow](https://kotlinlang.org/api/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-state-flow/) for sharing states
- [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) for dependency injection
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs
- [Room](https://developer.android.com/jetpack/androidx/releases/room) for local database
- MVVM/Clean Architecture
- Github
- [GraphQL APIs](https://graphql.org/learn/)

## Features

### Character Listing

The home screen of the app displays a list of 20 Rick and Morty characters. Each item in the list includes the character's name, status, and species. 

### Character Detail

Upon tapping on a character, the app navigates to a detailed screen. Here, the character's image and comprehensive information about their location is displayed. This includes the name, type, dimension, and number of residents in that location.


## Goals and requirements

The goals for the app architecture are:

* **Readability**: The code is designed to be easily comprehensible at a glance.
* **Maintainability**: The codebase is structured in a way that makes it straightforward to work on, even in the future.
* **Extensibility**: The architecture allows for the seamless addition of new features or making design adjustments.
*   Follow the [official architecture guidance](https://developer.android.com/jetpack/guide) as closely as possible.
*   Easy for developers to understand, nothing too experimental.
*   Support multiple developers working on the same codebase.
*   Minimize build times.


## Architecture overview

The app follows the Offline-First architecture, ensuring that once data is loaded, it's cached locally. This approach prioritizes displaying cached data first, followed by fetching the latest data from the remote source. Additionally, the app provides offline access for browsing, and it alerts the user with a snackbar when there is no internet connection.

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
[fetch_lister.webm](https://github.com/rishabharora3/FetchLister/assets/14349274/a969b0e1-546e-40c0-955b-7df1a48b569d)

## Open API
FetchLister using the [Rick and Morty API](https://rickandmortyapi.com/documentation/#graphql) for constructing GraphQL API.<br>

