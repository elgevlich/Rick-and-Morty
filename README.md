# Rick-and-Morty
An android app built using Kotlin and Java that consumes Rick and Morty API to display characters, episodes and locations.
## General description
The application consists of six main screens, as well as screens with filters that differ for different content.
The app supports back navigation. All details and filters screens display a back button.

All list tabs support Pull-to-Refresh.
When data is loaded, a progress indicator is displayed.

When the application starts, a Splash screen is shown with a picture symbolizing the application.

The main screen contains bottom navigation with 3 tabs:
- characters;
- locations;
- episodes.
Each tab has access to the search for this tab, as well as the filter.

Clicking on an item from the list opens a detail screen for selected object. Character - character details, location -
location details, episode - episode details.

Screens with filters are different for different types of content.
They contain options for filtering, as well as a button for applying the filter.

## Tech-stacks & libraries used:

* Kotlin
* Java
* Glide
* Coroutines
* RxJava
* Retrofit
* Pagging3
* Room
* Dagger2
* MVVM, Clean architecture

## App Screens
![app](https://github.com/elgevlich/Rick-and-Morty/assets/101803937/463b5cd2-1d59-4b64-8eef-d6de1bc6509d)

## API
All of the data and images presented in this app are sourced from [The Rick and Morty API](https://rickandmortyapi.com/).
