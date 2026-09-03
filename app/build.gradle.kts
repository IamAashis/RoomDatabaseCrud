plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.example.todoapproom"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.todoapproom"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)


//    implementation("androidx.room:room-runtime:2.8.3")
//    implementation("androidx.room:room-ktx:2.8.3")
//    ksp("androidx.room:room-compiler:2.8.3")
//
//    // Lifecycle (ViewModel + LiveData)
//    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.9.3")
//
//    // Coroutines
//    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.10.2")
//
//    // Legacy support
//    implementation("androidx.legacy:legacy-support-v4:1.0.0")

    // Room (database)
    implementation(libs.androidx.room.runtime)
    implementation(libs.androidx.room.ktx)
//    implementation(libs.androidx.room.rxjava2)  // optional if using RxJava
    ksp(libs.androidx.room.compiler)

    // Lifecycle (ViewModel + LiveData)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    // Coroutines
    implementation(libs.kotlinx.coroutines.android)

    // Legacy support
    implementation(libs.androidx.legacy.support.v4)
}