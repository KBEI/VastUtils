/*
 * Copyright 2022 VastGui
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

// Groovy --> KTS
// https://cloud.tencent.com/developer/article/1839887
// https://mp.weixin.qq.com/s/mVqShijGTExtQ_nLslchpQ

import com.android.build.gradle.internal.tasks.factory.dependsOn
import com.gcode.plugin.version.*

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("org.jetbrains.kotlin.android")
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
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"),"proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = "11"
    }

    viewBinding.isEnabled = true

    // https://www.jianshu.com/p/2a539b497460
    sourceSets["main"].java.srcDir("src/main/kotlin")
}

dependencies {
    implementation(AndroidX.activity_ktx)
    implementation(AndroidX.annotation)
    implementation(AndroidX.appcompat)
    implementation(AndroidX.arch_core_runtime)
    implementation(AndroidX.constraintlayout)
    implementation(AndroidX.core_ktx)
    implementation(AndroidX.fragment_ktx)
    implementation(AndroidX.lifecycle_livedata_core_ktx)
    implementation(AndroidX.lifecycle_livedata_ktx)
    implementation(AndroidX.lifecycle_runtime_ktx)
    implementation(AndroidX.lifecycle_viewmodel_ktx)
    implementation(AndroidX.test_monitor)
    implementation(AndroidX.recyclerview)
    androidTestImplementation(AndroidX.espresso_core)
    androidTestImplementation(AndroidX.junit)

    implementation(Jetbrains.kotlin_reflect)

    implementation(Google.material)

    implementation(Squareup.okhttp3)

    implementation(Libraries.progressmanager)
    testImplementation(Libraries.junit)
}

val JAR_PATH = "build/intermediates/runtime_library_classes_jar/release/"
val JAR_NAME = "classes.jar"
val DESTINATION_PATH = "libs"
val NEW_NAME = "VastTools_0.0.9_Cancey.jar"

tasks.register("makeJar",Copy::class){
    delete(DESTINATION_PATH + NEW_NAME)
    from(JAR_PATH + JAR_NAME)
    into(DESTINATION_PATH)
    rename(JAR_NAME, NEW_NAME)
}.dependsOn("build")

extra["PUBLISH_GROUP_ID"] = "io.github.sakurajimamaii"
extra["PUBLISH_ARTIFACT_ID"] = "VastTools"
extra["PUBLISH_VERSION"] = "0.0.9"
extra["PUBLISH_DESCRIPTION"] = "Easy Quick Android Tools for you to faster project development."
extra["PUBLISH_URL"] = "https://github.com/SakurajimaMaii/VastUtils/tree/master/libraries/VastTools"

apply(from = "${rootProject.projectDir}/publish-mavencentral.gradle")