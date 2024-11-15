plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.example.inscryption"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.inscryption"
        minSdk = 24
        targetSdk = 34
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

}

dependencies {
    //implementation("org.projectlombok:lombok:1.18.28")
    implementation("androidx.room:room-runtime:2.5.2")// Room - основная библиотека

    // Компилятор Room для аннотаций (необходим для Java)
    annotationProcessor("androidx.room:room-compiler:2.5.2")

    // Опционально: поддержка RxJava2, если нужна
    implementation("androidx.room:room-rxjava2:2.5.2")

    // Опционально: поддержка RxJava3, если нужна
    implementation("androidx.room:room-rxjava3:2.5.2")



    // Основная библиотека ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.7")

    // Для работы с LiveData
    implementation("androidx.lifecycle:lifecycle-livedata:2.8.7")

    // Для управления жизненным циклом компонентов
    implementation("androidx.lifecycle:lifecycle-runtime:2.8.7")
    
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

}