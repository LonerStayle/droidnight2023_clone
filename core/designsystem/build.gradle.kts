plugins {
    id("loner.android.library")
    id("loner.android.compose")
}

android {
    namespace = "kr.loner.core.designsystem"
}

dependencies {

    implementation(libs.androidx.appcompat)
    implementation(libs.landscapist.bom)
    implementation(libs.landscapist.coil)
    implementation(libs.landscapist.placeholder)
    implementation(libs.androidx.glance)
}