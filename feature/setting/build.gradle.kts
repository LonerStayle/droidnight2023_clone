plugins {
    id("loner.android.feature")
}

android {
    namespace = "kr.loner.feature.setting"
}

dependencies {

    implementation(libs.androidx.appcompat)

    implementation(libs.oss.licenses)
}