plugins {
    id("loner.android.feature")
}

android {
    namespace = "kr.loner.app2023.feature.main"
}

dependencies {
    implementation(projects.widget)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.runtimeCompose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.kotlinx.immutable)
}
