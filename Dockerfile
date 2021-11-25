FROM openjdk:11-jre

EXPOSE 8080

#add the jar file
ADD target/spring-boot-docker.jar spring-boot-docker.jar

# specify command to run the jar
ENTRYPOINT ["java","-jar","/spring-boot-docker.jar"]
