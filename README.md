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

# Screenshots
![alt text](https://lh3.googleusercontent.com/QwxDI5kHMGodsHZ45c8LH_5P1yHJhx9iPAudbkKGvaU38m7uIXEtGrLBYOt_7XYxj62mzPuNQx5mbdRnfNuuyw-Pff5wxFxUbCMdAutnZ-yPH_iFMkMzohTDF9gG5Ik5vj_icuMmqSf_PKVLOWIf4fSh3upMsiEwAdXIzqM3R9ODXQjUYVfiLKBOH-K0rubjVNBzzjAdrPj6ZzBmYZXag5aC5Dwcv4VdM4ofi5bNIhN6eViNjRORmFPrde2ulY0RcqT5xqYUPAAgO4yv_ogQ66HWueh-4OGuxn3gfVxRlWXZrVDlAnr4dam3b10xb2C71O5hKsqU4-MML1W2VmCZVdclVlWErdY_A17isOHpJbWTTN2JH49J_Glbq276VxQ6R1G_EYIs2J7iPZoYprb8JOU1DeudiKJAezBGGjw88xVsaY5N7cZ9Yj8y2wWLN8CqfXcEN7ltET4MNNNQLzs00bmhUfYCTqFAxBEhIXiamAElh6KLNFccQyT_BCylXncxccaKR5LhEtHwuNhPr3t6YJP1olgdFtASVHnz7yDlSYYXEgivMZYKn4Ltn5cTdq6sj0U2j2Qk67yRIdC7R5jmW0gwOlp2VGf9u-gWOYamagEKOoRaK3rzsOIteEsSuIE-P69gAYnaF90_AJdVAdExOZt6HsloDJHgzaLl1dEWXaBjosA_UjNqq08ebFQfjaIwiNKzDgGpraqK5NxceB80NNTDabHqUbdR3KRsNq9ImiyaRS9vW4P5SK5NA-5PwFKyT_9DoUi35B9uTZBK-D8o0FiqpE8odqvkp2_P=w401-h866-no?authuser=0)

# Side Notes
- This was my first repo: https://github.com/juancamilov06/FreshGIFs In some point the project got currupted in some way that NavComponent was not working at all. My solution was creating a new project and add all those files inside the new one. The first one, has some branching stuff and good commit history. This one not :D, I didn't want to spend my time on that but I recognize my fault.
- Nothing else, thanks for giving me the opportunity of making this test
