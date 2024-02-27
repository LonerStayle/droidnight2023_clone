import app.configureKotlinAndroid
import app.configureCoroutineAndroid
import app.configureHiltAndroid
import app.configureKotest

plugins {
    id("com.android.library")
    id("loner.verify.datekt")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()