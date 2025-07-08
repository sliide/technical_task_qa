plugins {
    `kotlin-dsl`
}
repositories {
    google()
    mavenCentral()
    mavenLocal()
}

dependencies {
    compileOnly(gradleApi())
    implementation(libs.android.gradleplugin)
    implementation(libs.kotlin.gradleplugin)
    implementation(libs.javapoet)
    
}

gradlePlugin {
    plugins {
        register("app-plugin") {
            id = "app-plugin"
            implementationClass = "AppPlugin"
        }
    }
}
