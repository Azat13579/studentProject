FROM maven:3.6-jdk-8-alpine AS build
WORKDIR /data
COPY . /data
RUN mvn clean package

FROM openjdk:8-jre-alpine AS final
WORKDIR /data
COPY --from=build /data/target/medProject-1.0-SNAPSHOT.jar /data/app.jar
ENTRYPOINT ["/bin/sh", "-c", "java -jar app.jar"]
