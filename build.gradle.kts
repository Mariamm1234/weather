
buildscript {
    dependencies {
        classpath ("com.android.tools.build:gradle:3.4.0")
        classpath ("com.google.gms:google-services:4.3.10")
        classpath ("com.google.firebase:firebase-crashlytics-gradle:2.9.0")
        classpath ("com.google.dagger:hilt-android-gradle-plugin:2.52")

    }
}
// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false


}