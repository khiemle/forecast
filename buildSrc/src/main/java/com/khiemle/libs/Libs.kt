package com.khiemle.libs

object Libs {
    object Gradle {
        private const val version = "4.0.1"
        const val android = "com.android.tools.build:gradle:$version"
    }

    object RootBeer {
        const val rootBeer = "com.scottyab:rootbeer-lib:0.0.8"
    }

    object Test {
        object Kluent {
            private const val version = "1.63"
            const val mock = "org.amshove.kluent:kluent-android:$version"
        }
    }

    object Kotlin {
        const val version = "1.4.32"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        object Coroutine {
            const val coroutineVersion = "1.4.3"
            const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
            const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutineVersion"
        }
    }

    object Network {
        object OkHttp3 {
            const val loggingInterceptor = "com.squareup.okhttp3:logging-interceptor:4.9.1"
        }

        object Retrofit2 {
            private const val version = "2.9.0"
            const val retrofit = "com.squareup.retrofit2:retrofit:$version"
            const val convertGson = "com.squareup.retrofit2:converter-gson:2.9.0"
        }
    }

    object Google {
        object Material {
            private const val version = "1.0.0"
            const val material = "com.google.android.material:material:$version"
        }

        object Dagger {
            private const val version = "2.29.1"
            const val dagger = "com.google.dagger:dagger:$version"
            const val daggerAndroid = "com.google.dagger:dagger-android:$version"
            const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:$version"
            const val daggerCompiler = "com.google.dagger:dagger-compiler:$version"
            const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:$version"

        }
        object Gson {
            const val gson = "com.google.code.gson:gson:2.8.6"
        }
    }

    object AndroidJetPack {
        object AndroidX {
            object Ktx {
                private const val activityVersion = "1.1.0"
                const val activityExtensions = "androidx.activity:activity-ktx:${activityVersion}"
            }
            object AppCompat {
                private const val version = "1.1.0"
                const val appCompat = "androidx.appcompat:appcompat:$version"
            }

            object ConstraintLayout {
                private const val version = "1.1.3"
                const val constraintLayout = "androidx.constraintlayout:constraintlayout:$version"
            }

            object RecyclerView {
                private const val version = "1.0.0"
                const val recyclerView = "androidx.recyclerview:recyclerview:$version"
            }

            object LifeCycle {
                private const val version = "2.2.0"
                const val liveData = "androidx.lifecycle:lifecycle-livedata:$version"
                const val viewModelExtensions = "androidx.lifecycle:lifecycle-viewmodel-ktx:${version}"
            }

            object Room {
                private const val roomVersion = "2.2.6"
                const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
                const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
                const val roomKtx = "androidx.room:room-ktx:$roomVersion"
            }

        }
    }

}