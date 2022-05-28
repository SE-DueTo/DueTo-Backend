###########
# builder #
###########
FROM maven:3.8.5-openjdk-17 AS builder

WORKDIR /dueto-builder
COPY . .
RUN mvn -DskipTests package

###########
#  main   #
###########
FROM openjdk:17-jdk

WORKDIR /dueto
COPY --from=builder /dueto-builder/target/**.jar ./

# specify command to run the jar
CMD ["java","-jar","/dueto/spring-boot-docker.jar"]
