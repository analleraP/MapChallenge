## Map Challenge

### How it's been done:

#### Language
Kotlin

#### Architecture
Based on Clean Architecture, divided into three layers:

* Data, with a remote data source
* Domain, for business model and repository.
* Feature, with Model-View-Presenter, + Interactor for use cases and Navigator for navigation.

#### Libraries
* coroutines (asynchronous programming)
* google maps
* fresco (remote image loading)
* retrofit (http client)
* moshi (JSON parser)
* dagger (dependency injection)

### Possible improvements
* Improve user permissions request
* Error handling
* More test!!
* Loading state