rootProject.name = "delivery"

include(
    "delivery-api",
    "delivery-application",
    "delivery-domain",
    "delivery-infrastructure:adapter-configuration",
    "delivery-infrastructure:adapter-persistence-jpa",
    "delivery-infrastructure:adapter-firebase-notification",
    "delivery-infrastructure:adapter-payment-gateway-momo",
)


pluginManagement {
    val kotlinVersion: String by settings
    val ktlintVersion: String by settings
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val asciidoctorVersion: String by settings
    val jibVersion: String by settings

    resolutionStrategy {
        eachPlugin {
            when (requested.id.id) {
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.jetbrains.kotlin.jvm",
                "org.jetbrains.kotlin.plugin.spring",
                "org.jetbrains.kotlin.kapt",
                "org.jetbrains.kotlin.plugin.jpa" -> useVersion(kotlinVersion)

                "org.jlleitschuh.gradle.ktlint" -> useVersion(ktlintVersion)
                "org.asciidoctor.jvm.convert" -> useVersion(asciidoctorVersion)
                "com.google.cloud.tools.jib" -> useVersion(jibVersion)
            }
        }
    }
}
