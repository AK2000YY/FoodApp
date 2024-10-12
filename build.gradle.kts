// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    //for libraries
    alias(libs.plugins.google.dagger) apply false
    alias(libs.plugins.kotlin.kapt) apply false
    //for firebase sdk
    alias(libs.plugins.google.gms)
}