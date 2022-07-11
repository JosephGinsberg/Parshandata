# Build node container
FROM node:18 AS build

EXPOSE 8080

WORKDIR /app

COPY ./frontend /app/frontend
RUN cd /app/frontend && npm install && npm run build

# Latest java image
FROM ibmjava:sdk-alpine as server

ENV DEBUG 0
ENV PLATFORM="docker"
ENV RESOURCE_PATH="/app/backend/src/main/resources/"

COPY ./backend /app/backend
WORKDIR /app/backend

COPY --from=build /app/frontend/build /app/backend/src/main/resources/public

RUN chmod +x mvnw
# RUN ./mvnw dependency:go-offline
RUN ./mvnw clean install
RUN ./mvnw -o package

CMD ["./mvnw", "spring-boot:run", "-Dserver.port=${PORT:8080}"]