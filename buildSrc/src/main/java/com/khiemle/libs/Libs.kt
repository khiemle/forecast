package com.khiemle.libs

object Libs {
    object Gradle {
        private const val version = "4.0.1"
        const val android = "com.android.tools.build:gradle:$version"
    }

    object Kotlin {
        const val version = "1.4.32"
        const val gradlePlugin = "gradle-plugin"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$version"
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
    }

    object AndroidJetPack {
        object AndroidX {
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
                private const val version = "2.1.0"
                const val runtime = "androidx.lifecycle:lifecycle-runtime:$version"
                const val extension = "androidx.lifecycle:lifecycle-extensions:$version"
                const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            }

        }


    }

}