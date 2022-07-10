build: docker build --tag java-docker .
web: docker run --rm -i -t -p 8080:8080 java-docker:latest