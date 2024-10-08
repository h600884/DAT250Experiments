FROM gradle:latest AS build

COPY settings.gradle.kts gradlew build.gradle.kts ./

COPY src src
COPY gradle gradle

RUN ./gradlew bootJar

RUN mv build/libs/Poll-app-0.0.1-SNAPSHOT.jar /app.jar

FROM openjdk:21-jdk-slim

COPY --from=build /app.jar /app.jar

EXPOSE 8080

CMD ["java", "-jar", "app.jar"]