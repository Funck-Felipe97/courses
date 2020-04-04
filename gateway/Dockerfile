FROM openjdk:11
VOLUME /app
COPY target/gateway-0.0.1-SNAPSHOT.jar gateway.jar
ENTRYPOINT ["java", "-jar", "/gateway.jar"]