FROM openjdk:8-jdk-alpine
EXPOSE 8081
COPY target/order-service-docker.war order-service-docker.war
ENTRYPOINT ["java","-jar","/order-service-docker.war"]