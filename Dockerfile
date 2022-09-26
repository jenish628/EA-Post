

FROM openjdk:17.0.2-jdk as deploy
MAINTAINER JENISH GHIMIRE
WORKDIR /app
COPY target/*.jar post.jar
CMD ["java","-jar","post.jar"]

