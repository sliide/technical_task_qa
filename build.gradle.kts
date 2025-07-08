// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.kotlinter)
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.hilt) apply false
}

buildscript {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
    dependencies {
        classpath(libs.hilt.gradleplugin)
        classpath(libs.kotlin.gradleplugin)
        classpath(libs.ktlint.compose)
        classpath(libs.kotlin.serialization.gradleplugin)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        mavenLocal()
    }
}