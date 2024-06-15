#FROM openjdk:17-jdk-slim
#VOLUME /tmp
#COPY target/*.jar app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

#FROM node:16 as build
#WORKDIR /frontend
#COPY package*.json ./
#RUN npm install
#COPY . .
#RUN npm run build
#
#FROM nginx:alpine
#COPY --from=build /frontend/build /usr/share/nginx/html
#EXPOSE 80
#CMD ["nginx", "-g", "daemon off;"]
