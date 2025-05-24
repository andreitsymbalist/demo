FROM openjdk:21
CMD ["./mvnw", "clean", "package"]
COPY target/demo-0.0.1-SNAPSHOT.jar demo-app.jar
ENTRYPOINT ["java","-jar","/demo-app.jar"]