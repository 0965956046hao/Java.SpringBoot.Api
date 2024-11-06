# build project
./mvnw clean install

# run project
./mvnw spring-boot:run

# docker run mysql 
docker run --name mysql8 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_USER=root -e MYSQL_PASSWORD=123456 -p 3306:3306 -d mysql:8.0
docker run --name mysql8 -e MYSQL_ROOT_PASSWORD=123456 -p 3306:3306 -d mysql:8.0