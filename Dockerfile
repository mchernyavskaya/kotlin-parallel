FROM openjdk:17-slim
LABEL authors="macherniavsk"
LABEL description="Docker image for the Spring Boot application"

ARG JAVA_CPUS=10
# set the java options
ENV JAVA_OPTS="-XX:ActiveProcessorCount=$JAVA_CPUS -Xms1200m -Xmx1200m -XX:+UseParallelGC -Xlog:gc -XX:+PrintCommandLineFlags -showversion"

RUN echo $JAVA_OPTS

# Run the jar file
COPY build/libs/kotlin-parallel-1.0-SNAPSHOT-all.jar app.jar
ENV JDK_JAVA_OPTIONS=$JAVA_OPTS
ENTRYPOINT ["java", "-jar", "/app.jar"]