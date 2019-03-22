FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER top3372@163.com

RUN mkdir -p /ins/gateway

WORKDIR /ins/gateway

EXPOSE 80

ADD ./target/api-gateway.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]