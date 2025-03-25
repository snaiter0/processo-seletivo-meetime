#build
FROM maven:3.9.9-amazoncorretto-21-al2023 AS build

WORKDIR /build

COPY . .

RUN mvn clean package -DskipTests

#run
FROM amazoncorretto:21.0.5
WORKDIR /app

COPY --from=build ./build/target/*.jar ./hubspot.jar

EXPOSE 8080

ENV TZ='America/Sao_Paulo'

ENTRYPOINT java -jar hubspot.jar