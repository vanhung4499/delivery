dependencies {
    val firebaseAdminVersion: String by properties
    implementation(project(":delivery-application"))
    implementation("com.google.firebase:firebase-admin:$firebaseAdminVersion")
}