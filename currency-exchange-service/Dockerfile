FROM openjdk:8-jre-alpine

LABEL maintainer="Dmytro Tkachenko"

ENV SERVICE_PARAM=1

COPY ./target/currency-exchange-service-*.jar /app/currency-exchange-service.jar

WORKDIR /app

EXPOSE 8000

CMD ["java", "-jar", "currency-exchange-service.jar"]