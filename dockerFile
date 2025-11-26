FROM maven:3.9.4-eclipse-temurin-21 AS builder

WORKDIR /app

COPY pom.xml .
RUN mvn dependency:resolve

COPY src ./src

COPY src/main/resources ./src/main/resources

RUN mvn clean package -DskipTests

FROM eclipse-temurin:21-jre AS runtime

WORKDIR /app

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 3042

ENTRYPOINT ["java", "-jar", "app.jar"]