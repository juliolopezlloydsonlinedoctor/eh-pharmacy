FROM openjdk:8-jre-alpine
EXPOSE 28610
VOLUME /tmp
COPY eh-pharmacy-ms/target/eh-pharmacy-ms-*.jar app.jar
COPY eh-pharmacy-ms/src/main/resources/application.properties application.properties
ENTRYPOINT ["java","-jar","/app.jar"]
