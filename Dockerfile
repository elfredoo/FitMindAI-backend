FROM jelastic/maven:3.9.5-openjdk-21 AS build

WORKDIR /app

# Skopiuj pliki projektu (najpierw pom.xml, potem resztę, by cache lepiej działał)
COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src
RUN mvn clean package -DskipTests

# Finalny obraz (mniejszy, bez narzędzi do budowania)
FROM openjdk:21-slim-bullseye

WORKDIR /app

COPY --from=build /app/target/sb-ecom-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
