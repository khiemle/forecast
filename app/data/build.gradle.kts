import com.khiemle.libs.Libs
plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.Kotlin.stdlib)
    implementation(project(":app:utilities"))
    implementation(project(":app:domain"))
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.mockito:mockito-core:3.5.7")
    testImplementation(Libs.Kotlin.Coroutine.test)
    testImplementation(Libs.Test.Kluent.mock)
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation(Libs.Google.Dagger.dagger)
    implementation(Libs.Google.Dagger.daggerAndroid)
    implementation(Libs.Google.Dagger.daggerAndroidSupport)
    kapt(Libs.Google.Dagger.daggerCompiler)
    kapt(Libs.Google.Dagger.daggerAndroidProcessor)

    implementation(Libs.Network.OkHttp3.loggingInterceptor)
    implementation(Libs.Network.Retrofit2.retrofit)
    implementation(Libs.Network.Retrofit2.convertGson)

    implementation(Libs.AndroidJetPack.AndroidX.Room.roomRuntime)
    implementation(Libs.AndroidJetPack.AndroidX.Room.roomKtx)
    kapt(Libs.AndroidJetPack.AndroidX.Room.roomCompiler)
}