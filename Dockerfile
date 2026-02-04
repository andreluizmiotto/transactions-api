FROM azul/zulu-openjdk:21 AS build
WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle settings.gradle ./

RUN chmod +x ./gradlew

COPY src ./src

RUN ./gradlew clean build -x test --no-daemon

FROM azul/zulu-openjdk:21-jre-headless
WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]