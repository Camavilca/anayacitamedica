FROM openjdk:8
EXPOSE 8080
ADD target/interviews.jar interviews.jar
ENTRYPOINT ["java","-jar","/interviews.jar"]