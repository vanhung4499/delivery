dependencies {
    val mockWebServerVersion: String by properties
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation(project(":delivery-domain"))
    implementation(project(":delivery-application"))
    testImplementation(testFixtures(project(":delivery-domain")))
    testImplementation("com.squareup.okhttp3:mockwebserver:$mockWebServerVersion")
    testImplementation("io.projectreactor:reactor-test")
}