import app.configureHiltAndroid
import app.configureKotestAndroid
import app.configureKotlinAndroid

plugins {
    id("com.android.application")
}

configureKotlinAndroid()
configureHiltAndroid()
configureKotestAndroid()