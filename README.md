# Image Stoarge in MySQL

- Compressed image before storing in database
- Decompressed image while retrieving from the database
- post call to store image : http://localhost:8080/image/ 
  
  body : form data type: file and upload image 


Implemented in Different ways:
1) In a local configuration
2) Dockerise our application and connect using docker-network using the Mysql docker image.
3) Docker composition

Local setup and access
---
1) application.properties pointing to the local mysql configuration
2) Run the application and check and post calls with Postman.

Create Mysql and spring-boot-app Docker images and run application in docker network
---
1) pull the mysql image from docker hub<br />
<code> docker pull mysql:5.7</code>
2) Create a docker network to communicate spring boot app and mysql db <br/>
<code>docker network create springboot-mysql-net</code>
3) run the mysql container in the network <br/>
<code>docker run --name mysqldb --network image-mysql-net -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=imagestore -
e MYSQL_USER=sa -e MYSQL_PASSWORD=1234 -d mysql:5.7
</code>
4) Verify that the container is operational<br/>  
<code> docker exec -it <container-id> bash</code>
<code> mysql -u<username> -p<password></code>
5) update the application.properties file
   spring.datasource.url=jdbc:mysql://mysqldb:3306/imagestore<br />
   spring.datasource.username=sa<br />
   spring.datasource.password=1234<br />
6) Create docker image of application
<code>mvn clean install</code>
<code>docker build . -t <image-name></code>
7) Run the docker application 
<code>docker run --network <network-name> --name <new-container-name> -p 8080:8080 -d <image-name></code>

Run Mysql and Application Docker images using docker-compose
---
1) Include the docker-compose.yml file.
2) Type <code> docker-compose up</code> to run docker compose.
3) Run MySQL Workbench and make a new connection with the properties listed in the app. properties file