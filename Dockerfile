# 빌드 스테이지
FROM gradle:7.4-jdk17-alpine as builder

WORKDIR /build

COPY build.gradle.kts settings.gradle.kts /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

COPY . /build
RUN gradle build -x test --parallel


# 실행 스테이지
FROM openjdk:17.0-slim
WORKDIR /app
# 애플리케이션 파일을 컨테이너에 복사
COPY --from=builder /build/build/libs/WithYou-0.0.1-SNAPSHOTjar /app.jar

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]
