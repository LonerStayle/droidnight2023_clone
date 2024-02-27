plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

dependencies {
    implementation(libs.kotlin.gradlePlugin)
    implementation(libs.android.gradlePlugin)
    implementation(libs.verify.detektPlugin)
}

gradlePlugin{
    plugins {
        register("androidHilt"){
            id = "loner.android.hilt"
            implementationClass = "kr.loner.app.HiltAndroidPlugin"
        }
        register("kotlinHilt"){
            id = "loner.kotlin.hilt"
            implementationClass = "kr.loner.app.HiltKotlinPlugin"
        }
    }
}