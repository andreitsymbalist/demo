FROM maven:latest as builder
WORKDIR /app
COPY mvnw pom.xml ./
COPY ./src ./src
RUN mvn clean package -DskipTests

FROM openjdk:21
WORKDIR /app
EXPOSE 8080
COPY --from=builder /app/target/*.jar /app/demo-app.jar
ENTRYPOINT ["java","-jar","/app/demo-app.jar"]