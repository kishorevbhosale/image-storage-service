FROM openjdk:11

COPY target/image-storage-service-0.0.1-SNAPSHOT.jar image-storage-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java","-jar","image-storage-service-0.0.1-SNAPSHOT.jar"]