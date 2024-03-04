plugins {
    id("loner.android.feature")
}

android {
    namespace = "kr.loner.feature.setting"
}

dependencies {

    implementation(libs.oss.licenses)
    implementation(libs.androidx.appcompat)
}