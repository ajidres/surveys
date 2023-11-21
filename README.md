# Android Movies Application

This is an Android Application to see surveys.

## Installation
Clone this repository and import into **Android Studio**
```bash
git clone https://github.com/ajidres/surveys
```

## Configuration
### Keystores:
Create a file `secrets.properties` under folder surveys with the following info:
```gradle
ENDPOINT_URL='...'
CLIENT_ID='...'
CLIENT_SECRET='...'
ENCRYPT_KEY='...'
ENCRYPT_ALGORITHM='...'
```

## Generating debug APK
From Android Studio:
1. ***Build*** menu
2. Build Bundle(s)/APK(s)

## Generating signed APK
From Android Studio:
1. ***Build*** menu
2. ***Generate Signed Bundle/APK...***
3. Fill in the keystore information *(you only need to do this once manually and then let Android Studio remember it)*

## Maintainers
This project is mantained by:
* [Andres Jimenez](https://github.com/ajidres)
