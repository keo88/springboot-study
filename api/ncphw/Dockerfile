FROM eclipse-temurin:21-jdk-alpine AS build
WORKDIR /workspace/app

ARG PROFILE=release

COPY settings.gradle ./
COPY build.gradle ./
COPY api/ncphw/build.gradle ./api/ncphw/
COPY gradle ./gradle
COPY gradlew ./
RUN chmod +x ./gradlew

COPY api/ncphw ./api/ncphw
RUN rm -rf ./api/ncphw/build || true

RUN ./gradlew api:ncphw:bootJar --no-daemon -Pprofile=${PROFILE}

FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

RUN mkdir -p /app/logs && \
    chmod 777 /app/logs

RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring

COPY --from=build /workspace/app/api/ncphw/build/libs/*.jar app.jar

ENV JAVA_OPTS=""

ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar --server.port=${PORT:8080}"]

EXPOSE 8080-8081

# Instructions to build and run the Docker container

# Local development command
# docker build -t keo1998/ncphw-local --build-arg PROFILE=local -f api/ncphw/Dockerfile .
# docker run -d -p 8080:8080 -e PORT=8080 --name ncphw-local-1 --rm keo1998/ncphw-local

#Release command
# docker build -t keo1998/ncphw-release -f api/ncphw/Dockerfile .
# or
# docker buildx build --platform linux/amd64,linux/arm64 -t keo1998/ncphw-release -f api/ncphw/Dockerfile --push .
# docker run -d -p 8080:8080 -e PORT=8080 --name ncphw-release-1 --rm keo1998/ncphw-release