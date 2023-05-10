plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    kotlin("plugin.serialization") version "1.8.20"
    id("com.android.library")
    id("org.jetbrains.compose")
    id("io.realm.kotlin") version "1.8.0"
}

kotlin {
    android()

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        version = "1.0.0"
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
            isStatic = true
        }
        extraSpecAttributes["resources"] = "['src/commonMain/resources/**', 'src/iosMain/resources/**']"
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.0")
                implementation(compose.runtime)
                implementation(compose.foundation)
                api(compose.material3)
                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                // Koin
                api("io.insert-koin:koin-core:3.4.0")

                // Voyager
                implementation("cafe.adriel.voyager:voyager-navigator:1.0.0-rc06")
                implementation("cafe.adriel.voyager:voyager-tab-navigator:1.0.0-rc06")

                // Ktor
                implementation("io.ktor:ktor-client-core:2.2.1")
                implementation("io.ktor:ktor-client-content-negotiation:2.2.1")
                implementation("io.ktor:ktor-serialization-kotlinx-json:2.2.1")
                implementation("io.ktor:ktor-client-json:2.2.1")
                implementation("io.ktor:ktor-client-logging:2.2.1")
                implementation("io.ktor:ktor-client-serialization:2.2.1")

                // Logger
                implementation("io.github.aakira:napier:2.6.1")

                // Realm
                implementation("io.realm.kotlin:library-base:1.8.0")
                implementation("io.realm.kotlin:library-sync:1.8.0")

                // Insets
                implementation("com.moriatsushi.insetsx:insetsx:0.1.0-alpha04")
            }
        }
        val androidMain by getting {
            dependencies {
                api("androidx.activity:activity-compose:1.7.1")
                api("androidx.appcompat:appcompat:1.6.1")
                api("androidx.core:core-ktx:1.10.0")

                api("io.ktor:ktor-client-android:2.2.1")
                implementation("io.coil-kt:coil-compose:2.3.0")
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation("io.ktor:ktor-client-darwin:2.2.1")
            }
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.myapplication.common"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
        targetSdk = (findProperty("android.targetSdk") as String).toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlin {
        jvmToolchain(11)
    }
}
