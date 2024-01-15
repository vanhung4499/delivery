dependencies {
    val querydslVersion: String by properties
    implementation("com.querydsl:querydsl-jpa:$querydslVersion")
    implementation("com.h2database:h2")
    implementation(project(":delivery-domain"))
    implementation(project(":delivery-application"))
    implementation(testFixtures(project(":delivery-domain")))
}
