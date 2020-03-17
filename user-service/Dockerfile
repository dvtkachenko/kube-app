FROM java:8-jdk-alpine
COPY ./target/*.jar /usr/app/user-service.jar
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-service.jar"]