FROM openjdk:8
EXPOSE 8080
ADD target/BankApp-1.0.jar bankapp.jar 
ENTRYPOINT ["java","-jar","/bankapp.jar"]