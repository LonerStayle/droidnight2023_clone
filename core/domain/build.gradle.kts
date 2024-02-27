
plugins {
    id("loner.android.library")
}

android {
    namespace = "kr.loner.core.domain"
}

dependencies {
    implementation(libs.inject)
    implementation(projects.core.data)
    implementation(projects.core.model)
}