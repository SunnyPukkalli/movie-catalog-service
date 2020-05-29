FROM openjdk:8
ADD target/movie-catalog.jar movie-catalog.jar
EXPOSE 8080
ENTRYPOINT exec java -jar movie-catalog.jar --spring.profiles.active=$PROFILE
