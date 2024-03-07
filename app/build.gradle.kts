plugins {
    id("loner.android.application")
    id("com.google.android.gms.oss-licenses-plugin")
}

android {
    namespace = "kr.loner.clonedroidnight2023"

    defaultConfig {
        applicationId = "kr.loner.clonedroidnight2023"
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes{
        getByName("release"){
            signingConfig = signingConfigs.getByName("debug")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(projects.feature.main)

    implementation(projects.core.designsystem)
    implementation(projects.core.navigation)
    implementation(projects.widget)
}