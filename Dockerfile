FROM openjdk:8-jdk-alpine

COPY /target/rps-ultimate-showdown-api.jar rps-ultimate-showdown-api.jar

EXPOSE 5000

ENTRYPOINT ["java", "-jar", "rps-ultimate-showdown-api.jar"]

# docker build -t my-api:auto .
# docker run -d -p 5000:5000 my-api:auto