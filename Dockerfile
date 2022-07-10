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

RUN chmod +x mvnw
# Installs most dependencies
# RUN ./mvnw dependency:go-offline
# Installs all dependencies
# RUN ./mvnw clean install

# COPY --from=build /app/frontend/build /app/backend/src/main/resources/public
COPY --from=build /app/frontend/build/_app /app/backend/src/main/resources/public/_app
COPY --from=build /app/frontend/build /app/backend/src/main/resources/templates

RUN ./mvnw package
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","/app.jar"]
# -Dserver.port=$PORT
