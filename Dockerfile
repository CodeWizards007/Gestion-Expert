FROM openjdk:17
EXPOSE 8091
ADD target/expert-1.0.jar expert-1.0.jar
ENTRYPOINT ["java","-jar","/expert-1.0.jar"]

