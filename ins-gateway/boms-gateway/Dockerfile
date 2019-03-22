FROM anapsix/alpine-java:8_server-jre_unlimited

MAINTAINER top3372@163.com

RUN mkdir -p /ins/boms

WORKDIR /ins/boms

EXPOSE 80

ADD ./target/boms-gateway.jar ./app.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "app.jar"]

CMD ["--spring.profiles.active=test"]