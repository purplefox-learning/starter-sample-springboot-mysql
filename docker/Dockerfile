
#easier to troubleshoot from an image with complete toolsets
#FROM picoded/ubuntu-openjdk-8-jdk
FROM openjdk:8-jre-slim

LABEL maintainer="chenming.yang@gmail.com"

EXPOSE 8888

RUN mkdir -p /opt/starter-sample-service
ADD starter-sample-springboot-mysql-1.0.0-exec.jar /opt/starter-sample-service/
WORKDIR /opt/starter-sample-service

ENTRYPOINT java $JAVA_OPTS -jar starter-sample-springboot-mysql-1.0.0-exec.jar


