# mvn clean install -DskipTests
# java -jar .\target\datasource-service-0.1.0.jar
# java -Djavax.net.debug=all -jar datasource-service.jar

# it seems -Dhttp.protocols param does not work, to check it
# java -Djavax.net.debug=all -Dhttps.protocols=TLSv1.3 -jar datasource-service.jar
# java -Djavax.net.debug=all -Dhttps.protocols=TLSv1.3 -Dhttps.cipherSuites=TLS_AES_256_GCM_SHA384 -jar datasource-service.jar
# java -Djavax.net.debug=all -Dhttps.protocols=TLSv1.1 -Dhttps.cipherSuites=TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA -jar datasource-service.jar
# java -Djavax.net.debug=all -Dhttps.protocols=TLSv1.3 -jar .\target\datasource-service-0.1.0.jar

# java -Djavax.net.debug=all -Djdk.tls.client.protocols=TLSv1.2 -jar datasource-service.jar
# unsupported TLSv1.2 cipher
# java -Djavax.net.debug=all -Djdk.tls.client.protocols=TLSv1.2 -Djdk.tls.client.cipherSuites=TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 -jar datasource-service.jar
# supported TLSv1.2 cipher
# java -Djavax.net.debug=all -Djdk.tls.client.protocols=TLSv1.2 -Djdk.tls.client.cipherSuites=TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256 -jar datasource-service.jar


# java -Djavax.net.debug=all -jar .\target\datasource-service-0.1.0.jar
# java -Djavax.net.debug=all -Djdk.tls.client.protocols=TLSv1.2 -jar .\target\datasource-service-0.1.0.jar
# java -Djavax.net.debug=all -Djdk.tls.client.protocols=TLSv1.2 -Djdk.tls.client.cipherSuites=TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384 -jar .\target\datasource-service-0.1.0.jar


# docker run -it -p 8100:8080 --name datasource-service-container --entrypoint "/bin/sh" --rm datasource-service
# docker run -it --name datasource-service-container --entrypoint "/bin/sh" --rm datasource-service
# docker container exec -it datasource-service-container /bin/sh

# rename file
# mv application-disabled.yaml application.yaml

# tcpdump curl example

# curl -vvv --tlsv1.2 --cacert /app/cert/cert.pem --request POST 'https://identity' --header 'Content-Type: application/json' --header 'Authorization: IdentityApiKey fb1b403bdf84-e80c-4dee' --header 'Accept: application/json' --data-raw '{}'
# curl -vvv --tlsv1.2 --ciphers ECDHE-ECDSA-AES256-GCM-SHA384 --request POST 'https://identity' --header 'Content-Type: application/json' --header 'Authorization: IdentityApiKey fb1b403bdf84-e80c-4dee' --header 'Accept: application/json' --data-raw '{}'
# curl -vvv --request POST 'https://identity' --header 'Content-Type: application/json' --header 'Authorization: Basic RUDIXWZaHJvZFJU6' --header 'Accept: application/json' --data-raw ''
# curl -vvv --tlsv1.0 --ciphers NULL-MD5 --request POST 'https://identity' --header 'Content-Type: application/json' --header 'Authorization: IdentityApiKey fb1b403bdf84-e80c-4dee' --header 'Accept: application/json' --data-raw '{}'

# tcpdump -vv -i eth0 -A port 443
# tcpdump -vv -i eth0 -x port 443

# cipher TLSv1.2, unsupported by identity server
# TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384    ECDHE-ECDSA-AES256-GCM-SHA384

# cipher TLSv1.2, supported by server
# TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256     ECDHE-RSA-AES128-GCM-SHA256

# !! try with JDK 13 !! it seems http client 11 do handshake in different way from HttpURLConnection

#FROM adoptopenjdk/openjdk13:jre-13.0.2_8-alpine
FROM adoptopenjdk/openjdk11:jre-11.0.6_10-alpine

RUN apk add --update \
      # Basic shell stuff
      bash \
      bash-completion \
      readline \
      grep \
      gawk \
      tree \
      # Interacting with the networks
      curl \
      wget \
      jq \
      drill \
      nmap \
      netcat-openbsd \
      socat \
      # Monitoring / Shell tools
      htop \
      mc \
      tcpdump

COPY ./target/*.jar /usr/app/datasource-service.jar
COPY ./config/application.yaml /usr/app/config/application-disabled.yaml
COPY ./secrets/secrets.yaml /usr/app/secrets/secrets.yaml
WORKDIR /usr/app
#ENV APP_ARGS="--spring.config.location=file:./config/,file:./secrets/secrets.yaml"
EXPOSE 8080
#ENTRYPOINT ["sh", "-c" ,"java $JAVA_OPTS -jar datasource-service.jar $APP_ARGS"]
ENTRYPOINT ["sh", "-c" ,"java -Djavax.net.debug=all -jar datasource-service.jar $APP_ARGS"]