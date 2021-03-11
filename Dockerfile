FROM openjdk:8
EXPOSE 8080
ADD target/BankApp-0.0.1-SNAPSHOT.jar docker-app.jar 
ENTRYPOINT ["java","-jar","/docker-app.jar"]