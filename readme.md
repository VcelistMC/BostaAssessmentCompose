
# Bosta Assessment - Android App

## Overview

This project is an Android application developed as part of a technical assessment for Bosta.

---

## Approach

### 1. **Architecture & Structure**
- **MVI Pattern:**  
  The app follows the Model-View-Intent (MVI) architecture, separating concerns between UI, business logic, and data sources.
- **Clean Code Principles:**  
  Code is organized into clear packages: `data`, `ui`, `viewmodels`, etc...
- **Base Classes:**  
  Common functionality for Fragments and ViewModels is abstracted into base classes (`BaseFragment`, `BaseViewModel`).

### 2. **Dependency Injection**
- **Hilt (Dagger):**  
  The project uses [Hilt](https://dagger.dev/hilt/) for dependency injection, simplifying the management of dependencies and promoting testability.


### 3. **Networking**
- **Retrofit & Gson:**  
  The app uses [Retrofit](https://square.github.io/retrofit/) for network requests and [Gson](https://github.com/google/gson) for JSON serialization/deserialization.
- **Repository Pattern:**  
  Data access is abstracted via a repository (`CityRepo`), which interacts with the network layer and provides data to the ViewModel.

### 4. **UI & User Experience**
- **Jetpack Compose:**
  - Modern declarative Android UI framework.

- **Search Functionality:**
  - Real-time filtering of cities and districts as the user types.
  - The search is case-insensitive and matches both city names and district names.
  - If a city matches, all its districts are shown; if only districts match, only those districts are shown under their city.

### 5. **Asynchronous Operations**
- **Coroutines:**  
  All network and data operations are performed asynchronously using Kotlin coroutines, ensuring a responsive UI.

---

## Libraries Used

| Library                | Purpose                                      |
|------------------------|----------------------------------------------|
| **Hilt (Dagger)**      | Dependency Injection                         |
| **Retrofit**           | Networking (HTTP client)                     |
| **Gson**               | JSON serialization/deserialization           |
| **Jetpack Compose**    | Modern Ui Framework                          |
| **Mockito**            | Mocking library                              |
| **JUnit**              | Testing library                              |

---


## Code Snippet: Search Logic

```kotlin
fun searchCities(text: String?) {
  val query = text?.lowercase()?.trim().orEmpty()

  if (query.isBlank()) {
    _cityList.postValue(fullCityList)
    return
  }

  val matchingCities = fullCityList.filter { city ->
    city.cityName.lowercase().startsWith(query) ||
            city.districts.any { district ->
              district.districtName.lowercase().startsWith(query)
            }
  }

  val refinedResults = matchingCities.map { city ->
    val cityNameMatches = city.cityName.lowercase().startsWith(query)

    if (cityNameMatches) {
      city
    } else {
      val matchingDistricts = city.districts.filter { district ->
        district.districtName.lowercase().startsWith(query)
      }
      city.copy(districts = matchingDistricts)
    }
  }

  _cityList.postValue(refinedResults)
}
```

---
