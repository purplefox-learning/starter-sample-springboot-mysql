rm docker/*.jar
./gradlew clean build
cp build/libs/starter-sample-springboot-mysql-1.0.0-exec.jar docker/
docker-compose -f docker/docker-compose-dev.yml build
docker-compose -f docker/docker-compose-dev.yml up
