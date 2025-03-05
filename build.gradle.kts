// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.google.gms.google.services) apply false
    id("org.jlleitschuh.gradle.ktlint") version "11.6.1" apply false
    id("com.diffplug.spotless") version "6.25.0" apply false

}