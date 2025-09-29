# Stage 1: Build
FROM maven:3.9.6-amazoncorretto-21-debian AS build

WORKDIR /app

# Copiar wrapper e pom.xml
COPY .mvn/ .mvn/
COPY mvnw mvnw.cmd pom.xml ./

# Dar permissão
RUN chmod +x mvnw

# Copiar código fonte
COPY src ./src

# Build do projeto
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM amazoncorretto:21-alpine

WORKDIR /app

# Copiar o JAR construído
COPY --from=build /app/target/service.tasks-0.0.1-SNAPSHOT.jar /app/app.jar

# Expor porta
EXPOSE 8081

# Rodar aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
