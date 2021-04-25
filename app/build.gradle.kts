import com.khiemle.libs.Libs
import java.util.Properties
plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(30)
    buildToolsVersion("30.0.3")

    buildFeatures {
        viewBinding = true
    }

    val properties = Properties()
    properties.load(project.file("env.properties").inputStream())
    val baseUrl = properties.getProperty("baseUrl")
    val appId = properties.getProperty("appId")
    val openWeatherPattern = properties.getProperty("openWeatherPattern")
    val pinCert = properties.getProperty("pinCert")
    val pinCertBackUpA = properties.getProperty("pinCertBackUpA")
    val pinCertBackUpB = properties.getProperty("pinCertBackUpB")


    defaultConfig {
        applicationId = "com.khiemle.nab"
        minSdkVersion(21)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        resValue(type = "string", name = "base_url", value = baseUrl )
        resValue(type = "string", name = "app_id", value = appId )

        resValue(type = "string", name = "open_weather_pattern", value = openWeatherPattern )
        resValue(type = "string", name = "pin_cert", value = pinCert )
        resValue(type = "string", name = "pin_cert_back_up_a", value = pinCertBackUpA )
        resValue(type = "string", name = "pin_cert_back_up_b", value = pinCertBackUpB )
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

    implementation(project(":app:domain"))
    implementation(project(":app:data"))
    implementation(project(":app:usecases"))
    implementation(project(":app:utilities"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.4.32")
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("com.google.android.material:material:1.3.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")

    implementation(Libs.AndroidJetPack.AndroidX.LifeCycle.liveData)
    implementation(Libs.AndroidJetPack.AndroidX.LifeCycle.viewModelExtensions)
    implementation(Libs.AndroidJetPack.AndroidX.Ktx.activityExtensions)

    implementation(Libs.Google.Dagger.dagger)
    implementation(Libs.Google.Dagger.daggerAndroid)
    implementation(Libs.Google.Dagger.daggerAndroidSupport)
    kapt(Libs.Google.Dagger.daggerCompiler)
    kapt(Libs.Google.Dagger.daggerAndroidProcessor)

    implementation(Libs.Kotlin.Coroutine.coroutines)
    implementation(Libs.Kotlin.Coroutine.coroutinesAndroid)

    testImplementation(Libs.Kotlin.Coroutine.test)
    testImplementation(Libs.Test.Kluent.mock)
    testImplementation("org.mockito:mockito-core:3.5.7")

    implementation(Libs.RootBeer.rootBeer)

    implementation(Libs.AndroidJetPack.AndroidX.Room.roomRuntime)
    implementation(Libs.AndroidJetPack.AndroidX.Room.roomKtx)
    kapt(Libs.AndroidJetPack.AndroidX.Room.roomCompiler)

}