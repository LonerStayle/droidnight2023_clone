plugins {
    id("loner.android.library")
}

android {
    namespace = "kr.loner.core.datastore"
}

dependencies {
    testImplementation(libs.junit4)
    testImplementation(libs.kotlin.test)
    implementation(libs.androidx.datastore)
}