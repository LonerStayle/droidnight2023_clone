import app.configureHiltAndroid
import app.libs
import gradle.kotlin.dsl.accessors._9885c8525475a2a77e0b650bdf1e3c81.implementation
import gradle.kotlin.dsl.accessors._9885c8525475a2a77e0b650bdf1e3c81.testImplementation
import org.gradle.kotlin.dsl.dependencies

plugins{
    id("loner.android.library")
    id("loner.android.compose")
}

android{
    defaultConfig{
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

configureHiltAndroid()

dependencies {
    implementation(project(":core:model"))
    implementation(project(":core:data"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:domain"))
    implementation(project(":core:navigation"))
    implementation(project(":core:ui"))

    testImplementation(project(":core:testing"))

    val libs = project.extensions.libs

    implementation(libs.findLibrary("hilt.navigation.compose").get())
    implementation(libs.findLibrary("androidx.compose.navigation").get())
    androidTestImplementation(libs.findLibrary("androidx.compose.navigation.test").get())

    implementation(libs.findLibrary("androidx.lifecycle.viewModelCompose").get())
    implementation(libs.findLibrary("androidx.lifecycle.runtimeCompose").get())
}