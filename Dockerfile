# Build node container
FROM node:18 AS build

EXPOSE 8080

WORKDIR /app

COPY ./frontend /app/frontend

RUN cd /app/frontend && npm install && npm run build

# Latest java image
FROM ibmjava:sdk-alpine as server

# RUN addgroup -S spring && adduser -S spring -G spring
# USER spring:spring


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

RUN for FILENAME in /app/backend/target/*.jar ; do mv $FILENAME ./target/app.jar ; done 

CMD ["java", "-Xmx300m", "-Xss512k", "-Dfile.encoding=UTF-8", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar", "./target/app.jar", "--server.port=${PORT:8080}"]
# -Dserver.port=$PORT

# # Build node container
# FROM node:18 AS build

# EXPOSE 8080

# WORKDIR /app

# COPY ./frontend /app/frontend

# RUN cd /app/frontend && npm install && npm run build

# # Latest java image
# FROM ibmjava:sdk-alpine as server

# ENV DEBUG 0
# ENV PLATFORM="docker"
# ENV RESOURCE_PATH="/app/backend/src/main/resources/"

# COPY ./backend /app/backend

# # COPY --from=build /app/frontend/build /app/backend/src/main/resources/public
# COPY --from=build /app/frontend/build/_app /app/backend/src/main/resources/public/_app
# COPY --from=build /app/frontend/build /app/backend/src/main/resources/templates

# WORKDIR /app/backend

# RUN chmod +x mvnw
# RUN ./mvnw package
# COPY /app/backend/target/*.jar /app/backend/app.jar

# ENTRYPOINT ["java","-jar","/app/backend/app.jar"]