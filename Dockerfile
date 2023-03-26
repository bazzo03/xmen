
FROM maven:3.8.4-openjdk-17 as build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean compile install

FROM maven:3.8.4-openjdk-17
COPY --from=build /usr/src/app/target/xmen-0.0.1-SNAPSHOT.jar  /usr/app/xmen-0.0.1-SNAPSHOT.jar
WORKDIR /usr/app
EXPOSE 5000
ENTRYPOINT ["java","-jar","xmen-0.0.1-SNAPSHOT.jar "]