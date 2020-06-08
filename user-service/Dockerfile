#FROM java:8-jdk-alpine
#FROM ubuntu:18.04
FROM ubuntu
#RUN apt-get update \
#RUN apt-get install dnsutils net-tools curl nmap netcat traceroute socat
RUN apt-get update \
    && apt-get install -y dnsutils net-tools curl nmap netcat traceroute socat

COPY ./target/*.jar /usr/app/user-service.jar
WORKDIR /usr/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "user-service.jar"]