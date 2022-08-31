FROM openjdk:11
VOLUME /tmp
ADD target/image-storage-service-0.0.1-SNAPSHOT.jar image-storage-service-0.0.1-SNAPSHOT.jar
EXPOSE 8084
ENTRYPOINT ["java","-jar","image-storage-service-0.0.1-SNAPSHOT.jar"]