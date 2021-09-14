# NASA Mars Rover Photos
------

You can display Mars photos has taken by rovers (Curiosity, Opportunity, Spirit). You can filter photos by camera also you can display all photos by clicking rover name on bottom navigation. You can click to the photo to see a photo details. 
Unfortunately you can display photos only choosen sol.(sol is a mars day which represent a day in earth for example sol 1 equals 06/08/2012)

## Technologies
------

- RecyclerView (infinite scroll for pagination)
- Fragments
- Mvvm archtitecture
- Dagger Hilt for dependency injection
- Coroutines for asynchronous operations
- Shared Preferences for onboarding settings (onboarding appears only first use)
- Retrofit and OkHttp for network operations


### Screenshots
-----

#### Splash, Onboarding, Filter
-----

<img src="./screenshots/OnboardingPhotosFilter.gif" height="500px" width="250px"/>

#### Photo Details, Filter
-----

<img src="./screenshots/PhotoDetails.gif" height="500px" width="250px"/>
