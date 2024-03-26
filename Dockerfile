FROM openjdk:17
VOLUME /tmp
ARG JAR_FILE=target/backend.jar
ADD ${JAR_FILE} backend.jar
ENTRYPOINT ["java","-jar","/backend.jar"]
EXPOSE 9091