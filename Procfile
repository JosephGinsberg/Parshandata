release : cd ./frontend && npm install && npm run build && cp -r ./frontend/build/* ../backend/src/main/resources/public
web: java $JAVA_OPTS -cp target/classes:target/dependency/* com.example.HelloWorld



