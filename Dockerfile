# syntax=docker/dockerfile:1

FROM eclipse-temurin:11-jdk-jammy AS base
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:resolve
COPY src ./src
RUN ./mvnw compile

FROM base AS development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=dev", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]

FROM base AS build
RUN ./mvnw package

FROM eclipse-temurin:11-jre-jammy AS production
EXPOSE 8080
COPY --from=build /app/target/cursomc*.jar /cursomc.jar
CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/cursomc.jar", "-Dspring-boot.run.profiles=prod"]
