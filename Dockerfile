FROM openjdk:17
COPY ./target/newbook-0.0.1-SNAPSHOT.jar /usr/src/app/
WORKDIR /usr/src/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "newbook-0.0.1-SNAPSHOT.jar"]