FROM openjdk:11
VOLUME /app
COPY target/auth.jar auth.jar
ENTRYPOINT ["java", "-jar", "auth.jar"]