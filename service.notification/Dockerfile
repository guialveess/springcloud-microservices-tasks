# Stage 1: Build
FROM maven:3.9.6-amazoncorretto-21-debian AS build

WORKDIR /app

# Copiar Maven wrapper e pom.xml para melhor caching
COPY .mvn/ .mvn/
COPY mvnw mvnw.cmd pom.xml ./

RUN chmod +x mvnw

# Copiar código fonte
COPY src ./src

# Build do projeto
RUN ./mvnw clean package -DskipTests

# Stage 2: Runtime
FROM amazoncorretto:21-alpine

WORKDIR /app

# Copiar o JAR construído no estágio de build
COPY --from=build /app/target/service.notification-0.0.1-SNAPSHOT.jar /app/app.jar

# Expor porta da aplicação
EXPOSE 8082

# Rodar aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
