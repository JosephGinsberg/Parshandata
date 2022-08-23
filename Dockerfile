# Latest java image
FROM ibmjava:sdk-alpine as server

ENV DEBUG 0
ENV PLATFORM="docker"
ENV RESOURCE_PATH="/app/backend/src/main/resources/"

COPY ./backend /app/backend
WORKDIR /app/backend

RUN chmod +x mvnw
RUN ./mvnw package

RUN for FILENAME in /app/backend/target/*.jar ; do mv $FILENAME app.jar ; done

EXPOSE 8080

CMD ["java", "-Xmx300m", "-Xss512k", "-Dfile.encoding=UTF-8", "-XX:+UseContainerSupport", "-Djava.security.egd=file:/dev/./urandom","-jar", "./app.jar", "--server.port=${PORT:8080}"]