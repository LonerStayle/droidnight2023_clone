
plugins {
    id("loner.android.feature")
}

android {
    namespace = "kr.loner.feature.bookmark"
}

dependencies {



    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.kotlinx.immutable)
}