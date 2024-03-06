# 빌드 스테이지
FROM gradle:7.4-jdk17-alpine as builder

WORKDIR /app

COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true


# 실행 스테이지
FROM openjdk:17.0-slim
WORKDIR /app
# 애플리케이션 파일을 컨테이너에 복사
COPY --from=builder /app/build/libs/WithYou-0.0.1-SNAPSHOT.jar /app.jar

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]
