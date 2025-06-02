FROM eclipse-temurin:8-jre

RUN mkdir -p /ssadmin-server
WORKDIR /imhqs-server

COPY ./target/imhqs-0.0.1-SNAPSHOT.jar app.jar

ENV TZ=Asia/Shanghai
ENV JAVA_OPTS="-Xms512m -Xmx512m -Djava.security.egd=file:/dev/./urandom"
ENV ARGS=""

EXPOSE 8080

CMD java ${JAVA_OPTS} -jar app.jar $ARGS