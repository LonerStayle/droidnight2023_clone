plugins {
    id("loner.kotlin.library")
    id("kotlinx-serialization")
}

dependencies {
    implementation(libs.kotlinx.serialization.json)
    api(libs.kotlinx.datetime)
}