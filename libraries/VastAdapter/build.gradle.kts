import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.gcode.plugin.version.*

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("com.gcode.plugin.version")
}

android {
    compileSdk = Version.compile_sdk_version

    defaultConfig {
        minSdk = Version.min_sdk_version
        targetSdk = Version.target_sdk_version

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        dataBinding = true
    }
}

dependencies {
    implementation(AndroidX.activity_ktx)
    implementation(AndroidX.annotation)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.arch_core_runtime)
    implementation(AndroidX.core_ktx)
    implementation(AndroidX.recyclerview)
    implementation(AndroidX.start_up_runtime)
    androidTestImplementation(AndroidX.espresso_core)
    androidTestImplementation(AndroidX.junit)

    implementation(Jetbrains.kotlin_reflect)

    implementation(Google.material)

    testImplementation(Libraries.junit)
}

// 打包生成class.jar
val JAR_PATH = "build/intermediates/runtime_library_classes_jar/release/" // 待打包文件的位置
val JAR_NAME = "classes.jar" // 待打包文件的名字
val DESTINATION_PATH = "libs" // 生成jar包的位置
val NEW_NAME = "VastAdapter_0.0.6_Cancey.jar" // 生成jar包的名字

tasks.register("makeJar", Copy::class){
    delete(DESTINATION_PATH + NEW_NAME)
    from(JAR_PATH + JAR_NAME)
    into(DESTINATION_PATH)
    rename(JAR_NAME, NEW_NAME)
}.dependsOn("build")

extra["PUBLISH_GROUP_ID"] = "io.github.sakurajimamaii"
extra["PUBLISH_ARTIFACT_ID"] = "VastAdapter"
extra["PUBLISH_VERSION"] = "0.0.6"
extra["PUBLISH_DESCRIPTION"] = "Help you quickly build an Adapter suitable for RecyclerView."
extra["PUBLISH_URL"] = "https://github.com/SakurajimaMaii/VastUtils/tree/master/libraries/VastAdapter"

apply(from = "${rootProject.projectDir}/publish-mavencentral.gradle")