#FROM openjdk:11
#VOLUME /tmp
#EXPOSE 9091
#ARG JAR_FILE=tagret/web-0.0.1-SNAPSHOT.jar
#ADD ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM maven:3.8.5-openjdk-17 AS build
#COPY ./ ./
#RUN mvn clean package -DskipTests
#
## Use OpenJDK 11 as the base image
#FROM openjdk:17-jdk-slim
#
## Set the working directory in the container
#WORKDIR /app
#
## Copy the packaged jar file into the container
#COPY target/web-0.0.1-SNAPSHOT.jar /app/app.jar
#
## Expose the port that your Spring Boot application uses
#EXPOSE 9091
#
## Command to run the Spring Boot application
#CMD ["java", "-jar", "app.jar"]


FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
COPY --from=build target/web-0.0.1-SNAPSHOT.jar web-0.0.1-SNAPSHOT.jar
EXPOSE 9091
ENTRYPOINT ["java","-jar","web-0.0.1-SNAPSHOT.jar"]
