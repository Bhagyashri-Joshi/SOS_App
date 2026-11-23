# SOS Location Sharing App

An Android application that shares the user's current location as a readable address through WhatsApp.

## Features

- Requests fine-location permission before accessing location.
- Retrieves the device's last known location using Google Play Services.
- Converts latitude and longitude into a readable address using Android Geocoder.
- Opens WhatsApp with a predefined emergency message and location.
- Displays status messages when location or address data is unavailable.
- Supports edge-to-edge UI and launcher integration.

## Tech Stack

- **Language:** Java 11
- **UI:** XML layouts with ConstraintLayout
- **Architecture:** Single-Activity architecture
- **Local storage:** None
- **Networking:** Android intents; no HTTP client
- **Backend/Firebase:** None
- **Key libraries:** AndroidX AppCompat, Activity, Material, ConstraintLayout, Google Play Services Location 21.3.0
- **Testing:** JUnit, AndroidX JUnit, Espresso
- **Build:** Gradle 8.13.2 Kotlin DSL

## Project Structure

```text
SOS_App/
├── app/src/main/java/com/example/acclocation/
│   └── MainActivity.java    # Location permissions and WhatsApp sharing flow
├── app/src/main/res/layout/
│   └── activity_main.xml    # Main screen with the Help button
├── app/src/main/res/values/
│   └── themes.xml           # Application theme resources
├── app/src/main/AndroidManifest.xml
└── app/build.gradle.kts     # Android configuration and dependencies
```

## Getting Started

### Prerequisites

- Android Studio
- JDK 11
- Android device or emulator with Google Play Services
- WhatsApp installed for sharing
- Minimum SDK: 24
- Target SDK: 36
- Compile SDK: 36

### Clone and Run

```bash
git clone https://github.com/Bhagyashri-Joshi/SOS_App.git
cd SOS_App
```

1. Open the project in Android Studio.
2. Allow Gradle to download dependencies.
3. Connect an Android device or start an emulator.
4. Enable location services.
5. Run the `app` configuration.

### Setup

- Grant fine-location permission when prompted.
- `google-services.json`: Not required; no Firebase or Google configuration file is used.
- API keys: None found in the project.

## How It Works

The app starts in `MainActivity` and displays a single **Help** button.  
The button checks fine-location permission and requests it when necessary.  
After permission is granted, Google Play Services retrieves the last known location.  
Android Geocoder converts it into an address, and an explicit WhatsApp intent sends the emergency message.

## Challenges and Learnings

- Implemented runtime location permission handling before retrieving the user's position.
- Handled missing location and address results with short user-facing messages.
- Used Android intent filtering to launch WhatsApp directly with prepared text.

## Future Improvements

- Continue location updates instead of retrieving only the last known location.
- Add a fallback that shares coordinates if address resolution fails.
- Add automated tests for permission, location, and intent behavior.

## Author

**Bhagyashri Joshi**
