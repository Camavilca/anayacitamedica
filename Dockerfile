FROM openjdk:8
EXPOSE 8080
ADD target/hospital.jar hospital.jar
ENTRYPOINT ["java","-jar","/hospital.jar"]