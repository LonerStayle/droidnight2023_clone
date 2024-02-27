import app.configureKotlinAndroid
import app.configureCoroutineAndroid
import app.configureHiltAndroid
import app.configureKotest
import app.configureKotlinAndroid

plugins {
    id("loner.android.library")
    id("loner.verify.datekt")
}

configureKotlinAndroid()
configureKotest()
configureCoroutineAndroid()
configureHiltAndroid()