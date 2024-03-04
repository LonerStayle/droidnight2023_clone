plugins {
    id("loner.android.feature")
}

android {
    namespace = "kr.loner.feature.session"
}

dependencies {
    implementation(libs.kotlinx.immutable)
    implementation(projects.widget)
}