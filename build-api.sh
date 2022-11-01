./gradlew build -Dorg.gradle.java.home="C:\\Program Files\\Java\\jdk-17.0.2"
docker build --platform linux/amd64 -t pisproject-api .
docker-compose up -d