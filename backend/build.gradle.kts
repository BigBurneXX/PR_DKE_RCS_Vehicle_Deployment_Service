plugins {
	java
	id("org.springframework.boot") version "3.2.5"
	id("io.spring.dependency-management") version "1.1.5"
	id("io.freefair.lombok") version "8.6"
}

var optaplannerVersion = "9.44.0.Final"

group = "com.example"
version = "0.0.2-SNAPSHOT"

java {
	sourceCompatibility = JavaVersion.VERSION_17
}

repositories {
	mavenCentral()
}

dependencies {
	implementation("com.oracle.database.jdbc:ojdbc10:19.22.0.0")
	// https://mvnrepository.com/artifact/org.optaplanner/optaplanner-core
	implementation("org.optaplanner:optaplanner-core:${optaplannerVersion}")
	// https://mvnrepository.com/artifact/org.optaplanner/optaplanner-persistence-jpa
	implementation("org.optaplanner:optaplanner-persistence-jpa:${optaplannerVersion}")
	// https://mvnrepository.com/artifact/org.optaplanner/optaplanner-spring-boot-starter
	implementation("org.optaplanner:optaplanner-spring-boot-starter:${optaplannerVersion}")
	implementation("org.projectlombok:lombok:1.18.32")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	// for testing purpose when oracle is down
	runtimeOnly("com.h2database:h2")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
