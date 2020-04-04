FROM openjdk:11
VOLUME /app
COPY target/discovery.jar discovery.jar
ENTRYPOINT ["java", "-jar", "/discovery.jar"]