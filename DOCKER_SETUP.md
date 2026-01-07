# Docker Setup Guide for WSL

This guide will help you run the entire application (Vue frontend + Spring Boot backend + PostgreSQL) using Docker containers on WSL.

## What's Included

- **Vue 3 Frontend** (built and served by Spring Boot)
- **Spring Boot Backend** (Java 25)
- **PostgreSQL Database**

## Prerequisites

1. **Docker Desktop for Windows** with WSL 2 backend
   - Download from: https://www.docker.com/products/docker-desktop
   - Ensure WSL 2 is enabled in Docker Desktop settings

2. **WSL 2 Installation**
   - Windows 10 (version 1903 or higher) or Windows 11
   - Install WSL: `wsl --install`

## Quick Start

### 1. Clone/Navigate to Project Directory in WSL

```bash
# Open WSL terminal
wsl

# Navigate to your project
cd /mnt/c/Users/kdkoe/OneDrive/Documents/IP\ Werk/Individual-Project
```

### 2. Build and Run with Docker Compose

```bash
# Build and start both PostgreSQL and the application
docker-compose up --build

# Or run in background
docker-compose up -d --build
```

### 3. Access the Application

- **Frontend**: http://localhost:8080
- **Backend API**: http://localhost:8080/api/* 
- **PostgreSQL**: localhost:5432

### 4. View Logs

```bash
# View logs from all services
docker-compose logs -f

# View logs from specific service
docker-compose logs -f app
docker-compose logs -f postgres
```

## Common Commands

### Stop Containers
```bash
docker-compose down
```

### Stop and Remove Data
```bash
docker-compose down -v
```

### Rebuild Without Cache
```bash
docker-compose up --build --no-cache
```

### Run Only PostgreSQL (useful for development)
```bash
docker-compose up -d postgres
```

Then run the frontend and backend separately on your machine for faster development cycles.

### Access PostgreSQL Container Directly
```bash
docker exec -it ip_postgres psql -U ip_user -d ip_database
```

## Environment Variables

You can modify the database credentials in `docker-compose.yml`:

```yaml
environment:
  POSTGRES_DB: ip_database
  POSTGRES_USER: ip_user
  POSTGRES_PASSWORD: ip_password
```

Or override them when running:

```bash
POSTGRES_PASSWORD=newpassword docker-compose up --build
```

## Production Deployment Notes

Before deploying to production:

1. Update credentials in `application-prod.properties`
2. Change database credentials in `docker-compose.yml`
3. Update `SPRING_JPA_HIBERNATE_DDL_AUTO` to `validate` instead of `update`
4. Use proper secrets management (Docker Secrets, environment files, etc.)

## Troubleshooting

### Container won't start
```bash
docker-compose logs app
```

### Database connection refused
- Wait for PostgreSQL to be healthy (healthcheck waits 5-10 seconds)
- Check PostgreSQL logs: `docker-compose logs postgres`

### Port already in use
Edit `docker-compose.yml` to use different ports:
```yaml
ports:
  - "8080:8080"  # Change first number for external port
```

### WSL Network Issues
Ensure WSL 2 is using bridged networking:
```bash
wsl --list --verbose  # Check version
```and Running Individually

### Option 1: Build and Run Together (Recommended)
```bash
docker-compose up --build
```

### Option 2: Build Backend and Frontend Separately, Then Run
```bash
# Build backend only
docker build -t ip-app:latest .

# Run with Docker Compose
docker-compose up
```

### Option 3: Development Mode (Backend + PostgreSQL in Docker, Frontend on Host)
```bash
# Start only PostgreSQL and backend in Docker
docker-compose up -d postgres app

# In separate terminal, run frontend locally
cd frontend
npm install
npm run dev
```

This is faster for frontend development since Vite has hot reload.ker run -d --name postgres --network ip_network -e POSTGRES_PASSWORD=ip_password postgres:16-alpine
docker run -d --network ip_network -p 8080:8080 -e SPRING_DATASOURCE_URL=jdbc:postgresql://postgres:5432/ip_database ip-app:latest
```

## Additional Resources

- Docker Docs: https://docs.docker.com/
- Docker Compose: https://docs.docker.com/compose/
- WSL Documentation: https://learn.microsoft.com/en-us/windows/wsl/
