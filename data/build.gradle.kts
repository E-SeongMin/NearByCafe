import java.util.Properties

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kapt)
}

val localProperties = Properties().apply {
    rootProject
        .file("local.properties")
        .inputStream()
        .use { load(it) }
}

android {
    namespace = "com.geek.data"
    compileSdk = 35

    defaultConfig {
        minSdk = 21
        buildConfigField(
            "String", "KAKAO_REST_API_KEY",
            "\"${localProperties["KAKAO_REST_API_KEY"]}\""
        )
        buildConfigField(
            "String", "NAVER_CLIENT_ID",
            "\"${localProperties["NAVER_CLIENT_ID"]}\""
        )
        buildConfigField(
            "String", "NAVER_CLIENT_SECRET",
            "\"${localProperties["NAVER_CLIENT_SECRET"]}\""
        )
    }

    buildFeatures { buildConfig = true }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions { jvmTarget = "21" }
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.converter.scalars)
    implementation(libs.logging.interceptor)

    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)
}