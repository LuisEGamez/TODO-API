import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.1.5"
	id("io.spring.dependency-management") version "1.1.3"
	id("org.flywaydb.flyway") version "9.1.6"
	kotlin("jvm") version "1.8.22"
	kotlin("plugin.spring") version "1.8.22"
	kotlin("plugin.jpa") version "1.9.20"
}

group = "com.todo-api"
version = "0.0.1-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.springframework.boot:spring-boot-starter-actuator")
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")


	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework:spring-jdbc:5.3.22")
	implementation("org.postgresql:postgresql:42.4.3")

	implementation("org.flywaydb:flyway-core:8.5.13")

	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("io.mockk:mockk:1.12.5")
	testImplementation("org.testcontainers:testcontainers:1.19.1")
	testImplementation("org.testcontainers:jdbc:1.17.3")
	testImplementation("org.testcontainers:junit-jupiter:1.17.3")
	testImplementation("org.testcontainers:postgresql:1.17.3")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs += "-Xjsr305=strict"
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

flyway {
	val host = System.getenv("POSTGRE_URL") ?: "localhost"
	val port = "5433"

	url = "jdbc:postgresql://$host:$port/todo_database"
	user = System.getenv("POSTGRE_USERNAME") ?: "postgres"
	password = System.getenv("POSTGRE_PASSWORD") ?: "postgres"
}
