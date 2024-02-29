@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    id("loner.android.library")
    id("loner.android.compose")
}

android {
    namespace = "kr.loner.widget"
}

dependencies {

    implementation(libs.androidx.glance)
    implementation(libs.androidx.glance.appwidget)
    implementation(libs.glance.tools.appwidget.host)

    implementation(projects.core.designsystem)
    implementation(projects.core.domain)

    implementation(project(mapOf("path" to ":core:model")))
}