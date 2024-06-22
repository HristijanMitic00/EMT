#FROM maven:3.8.5-openjdk-17 AS build
#COPY ./src ./
#RUN mvn clean package -DskipTests

#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]
#COPY target/web-0.0.1-SNAPSHOT.jar web-0.0.1-SNAPSHOT.jar
#EXPOSE 9091
#ENTRYPOINT ["java","-jar","web-0.0.1-SNAPSHOT.jar"]
