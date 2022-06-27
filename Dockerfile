FROM maven:3.8.6-openjdk-11-slim AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -B -f pom.xml clean package -DskipTests

FROM openjdk:11-jre-slim
COPY --from=build /workspace/target/*.jar demo-app.jar
EXPOSE 8081
ENTRYPOINT ["java","-jar","demo-app.jar"]
