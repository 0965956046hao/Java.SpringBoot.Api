# build project
./mvnw clean install 
mvn clean install 

# run project
./mvnw spring-boot:run
mvn spring-boot:run
# docker run mysql 
docker run --name mysql8 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=root -e MYSQL_PASSWORD=123456 -p 3306:3306 -d mysql:8.0
docker run --name mysql8 --network test-network -e MYSQL_ROOT_PASSWORD=123456 -p 3308:3306 -d mysql:8.0

docker network create test-network

docker build -t springbootapi:0.0.1 .
docker run --name identity-service --network devteria-network -p 8080:8080 -e DBMS_CONNECTION=jdbc:mysql://mysql:3306/identity_service identity-service:0.9.0

$env:DB_CONNECT="jdbc:mysql://mysql8:3308/spring"; & ./start-dev.sh
