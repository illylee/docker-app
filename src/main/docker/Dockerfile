FROM openjdk:8-jdk
MAINTAINER kyoungjin.illy@gmail.com
VOLUME /tmp
RUN mkdir -p /app/
COPY docker-app-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]