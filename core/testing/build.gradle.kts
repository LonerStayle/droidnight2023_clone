plugins {
    id("loner.android.library")
}

android {
    namespace = "kr.loner.core.testing"
}

dependencies {
    api(libs.junit4)
    api(libs.junit.vintage.engine)
    api(libs.kotlin.test)
    api(libs.mockk)
    api(libs.turbine)
    api(libs.coroutines.test)
}