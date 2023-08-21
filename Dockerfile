
FROM adoptopenjdk/openjdk8:ubuntu-jre

ENV TZ=Asia/Singapore

RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY ./target/sprintboot-demo-0.0.1-SNAPSHOT.jar app.jar

# Set the command to be run by default
ENTRYPOINT ["java", "-jar", "app.jar"]

# Set default arguments to be passed to the command
CMD []
