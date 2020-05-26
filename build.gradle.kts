import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

repositories {
    mavenCentral()
//    maven(url = uri("https://repo.spring.io/milestone"))
//    maven(url = uri("https://repo.spring.io/snapshot"))
    jcenter()
}

plugins {
    application
    id("org.springframework.boot") version "2.3.0.RELEASE"
    id("io.spring.dependency-management") version "1.0.9.RELEASE"
    kotlin("jvm") version "1.3.72"
}

group = "ru.chsergeig.bot"
version = "1.0.0a"

dependencies {
    implementation(kotlin("stdlib-jdk8"))

    implementation(group = "org.springframework.boot", name = "spring-boot-starter-web")

    implementation(group = "me.ramswaroop.jbot", name = "jbot", version = "4.1.0")
    implementation(group = "me.ramswaroop.jbot", name = "jbot-core", version = "3.0.0")
    implementation(group = "com.cdancy", name = "jenkins-rest", version = "0.0.27")

}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

configure<JavaApplication> {
    mainClassName = "ru.chsergeig.bot.slack.MainKt"
    applicationDefaultJvmArgs  = listOf("-Xdebug", "-Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=8222")
}
