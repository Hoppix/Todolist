#
# Run stage stage
#
FROM openjdk:11-jre-slim
COPY target app
EXPOSE 8083
CMD java -jar target/JavaCI-0.0.2-SNAPSHOT.jar