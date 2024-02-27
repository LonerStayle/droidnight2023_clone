plugins {
    id("loner.android.library")
    id("loner.android.compose")
}

android {
    namespace = "kr.loner.core.ui"
}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.designsystem)
}
