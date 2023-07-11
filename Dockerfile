FROM openjdk:20-jdk
VOLUME /tmp
ADD target/kayaspring-0.0.2-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
EXPOSE 8080
