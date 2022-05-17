# FreshPIC
FreshWorks challenge app

# Core
The app is build in a multi-module project, with a CLEAN Architecture from a global perspective and a MVVM architechture for the View-UI layer

# Used Technologies
- Android
- Hilt
- Retrofit 2
- Room
- Proto DataStore
- Glide
- JUnit 4
- Mockito
- Coroutines
- Navigation Component
- Material Components
- Stetho

# Main Functionalities
- Fetch all the trending GIFs from API (I took a risk here :D I wanted to make two independent screens, with different look and feel and aslo different functionalities)
- Fetch a list of GIFs based on a query
- Mark GIFs as Favorites and also have them displayed in an independent screen
- Age Control (I noticed the Giphy API receives an argument called rating which determines how explicit the results are, so in the first install the app asks the user if is adult or not)
- Day/Night theme support ✨
- Screen orientation support

# Side Notes
- This was my first repo: https://github.com/juancamilov06/FreshGIFs In some point the project got currupted in some way that NavComponent was not working at all. My solution was creating a new project and add all those files inside the new one. The first one, has some branching stuff and good commit history. This one not :D, I didn't want to spend my time on that but I recognize my fault.
- Nothing else, thanks for giving me the opportunity of making this test
