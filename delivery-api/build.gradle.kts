import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks
val snippetsDir by extra { file("build/generated-snippets") }
val asciidoctorExt: Configuration by configurations.creating

bootJar.enabled = true
bootJar.duplicatesStrategy = DuplicatesStrategy.EXCLUDE
jar.enabled = false

plugins {
    id("org.asciidoctor.jvm.convert")
    id("com.google.cloud.tools.jib")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation(project(":delivery-application"))
    implementation(project(":delivery-domain"))
    implementation(project(":delivery-infrastructure:adapter-persistence-jpa"))
    implementation(project(":delivery-infrastructure:adapter-configuration"))
    implementation(project(":delivery-infrastructure:adapter-firebase-notification"))

    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    asciidoctorExt("org.springframework.restdocs:spring-restdocs-asciidoctor")
}

tasks.test {
    outputs.dir(snippetsDir)
}

tasks.asciidoctor {
    configurations("asciidoctorExt")
    inputs.dir(snippetsDir)
    dependsOn(tasks.test)
    sources {
        include("**/index.adoc")
    }
    baseDirFollowsSourceFile()
}

jib {
    from {
        image = "adoptopenjdk/openjdk11:alpine-jre"
    }

    to {
        image = "hnv99/delivery-api"
        tags = setOf("${project.version}")
    }

    container {
        creationTime = "USE_CURRENT_TIMESTAMP"
        mainClass = "com.hnv99.delivery.DeliveryApiApplicationKt"
        jvmFlags = listOf(
            "-Xms512m",
            "-Xmx512m",
            "-Xdebug",
            "-XshowSettings:vm",
            "-XX:+UnlockExperimentalVMOptions",
            "-XX:+UseContainerSupport",
            "-Dfile.encoding=UTF-8"
        )
        environment = mapOf("SPRING_OUTPUT_ANSI_ENABLED" to "ALWAYS")
        ports = listOf("8080")
    }
}
