# Stage 1: build
# Start with a Maven image that includes JDK 21
FROM maven:3.9.9-ibm-semeru-17-focal AS build

# Copy source code and pom.xml file to /app folder
WORKDIR /app
ARG CONTAINER_PORT
COPY pom.xml .
COPY src ./src

# Build source code with maven
RUN mvn package -DskipTests

#Stage 2: create image
# Start with Amazon Correto JDK 21
FROM ibm-semeru-runtimes:open-17-jdk-focal

# Set working folder to App and copy complied file from above step
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE ${CONTAINER_PORT}
# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]