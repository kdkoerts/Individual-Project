# Stage 1: Build backend
FROM maven:3.9-eclipse-temurin-25 AS backend-builder

WORKDIR /build

COPY . .

RUN mvn clean install -DskipTests -q

# Stage 2: Build frontend
FROM node:22-alpine AS frontend-builder

WORKDIR /frontend-build

COPY frontend/package*.json ./

RUN npm ci

COPY frontend .

RUN npm run build

# Stage 3: Runtime
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy backend JAR
COPY --from=backend-builder /build/web/target/web-*.jar app.jar

# Copy frontend dist files
COPY --from=frontend-builder /frontend-build/dist /app/static

EXPOSE 8080

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "app.jar"]
