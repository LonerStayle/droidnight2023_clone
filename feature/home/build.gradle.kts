plugins {
    id("loner.android.feature")
}

android {
    namespace = "kr.loner.feature.home"
}

dependencies {
    implementation(libs.kotlinx.immutable)
    implementation(libs.compose.shimmer)
}