plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
    id("com.google.protobuf") version "0.9.4"
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
}


android {
    namespace = "com.nimble.surveys"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.nimble.surveys"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.3"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }

    secrets {
        propertiesFileName = "secrets.properties"
        ignoreList.add("keyToIgnore")
        ignoreList.add("sdk.*")
    }

}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")
    implementation("androidx.appcompat:appcompat:1.6.1")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

//    SPLASH
    implementation("androidx.core:core-splashscreen:1.0.1")

//    CONSTRAINT
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")

//    NAVIGATION FRAGMENT
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.4")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.4")

//    HILT
    implementation("com.google.dagger:hilt-android:2.47")
    kapt("com.google.dagger:hilt-android-compiler:2.46.1")

//    RETROFIT
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1")

//    PROTO
    implementation("androidx.datastore:datastore:1.0.0")
    implementation("com.google.protobuf:protobuf-javalite:3.18.0")

//    LIVEDATA
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")

//    SWIPEREFRESH
    implementation ("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")

//    VIEWPAGER
    implementation("androidx.viewpager2:viewpager2:1.0.0")

//    GLIDE
    implementation ("com.github.bumptech.glide:glide:4.16.0")

//    PAGER INDICATOR
    implementation("com.tbuonomo:dotsindicator:5.0")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:21.0-rc-1"
    }
    generateProtoTasks {
        all().forEach { task ->
            task.plugins {
                create("java") {
                    option("lite")
                }
            }
        }
    }
}