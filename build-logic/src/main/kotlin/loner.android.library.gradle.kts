import app.configureCoroutineAndroid
import app.configureHiltAndroid
import app.configureKotest
import app.configureKotlinAndroid

plugins {
    id("com.android.library")
    id("loner.verify.datekt")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()