plugins {
    alias(libs.plugins.kotlin.jvm)
    alias(libs.plugins.kapt)
    alias(libs.plugins.micronaut.application)
    alias(libs.plugins.shadow)
    alias(libs.plugins.detekt)
    alias(libs.plugins.kotlinter)
}

version = "0.1"
group = "com.leeturner.spektrum"

repositories {
    mavenCentral()
}

dependencies {
    kapt(libs.picocli.codegen)
    implementation(libs.picocli)
    implementation(libs.micronaut.picocli)
    runtimeOnly(libs.logback.classic)

    testImplementation(libs.junit.platform.suite)
    testImplementation(libs.strikt.core)
}

application {
    mainClass = "com.leeturner.spektrum.SpektrumCommand"
}

kotlin {
    jvmToolchain(25)
}

detekt {
    toolVersion = libs.versions.detekt.get()
    config.setFrom(file("config/detekt/detekt.yml"))
    buildUponDefaultConfig = true
}

micronaut {
    version(libs.versions.micronaut.version.get())
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("com.leeturner.spektrum*")
    }
}
