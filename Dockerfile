FROM openjdk:17-slim
LABEL authors="macherniavsk"
LABEL description="Docker image for the Spring Boot application"

# Run the jar file
COPY build/libs/kotlin-parallel-1.0-SNAPSHOT-all.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]