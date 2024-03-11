# 기본 이미지로 OpenJDK 17을 사용
FROM openjdk:17

# 애플리케이션 파일을 컨테이너에 복사
COPY build/libs/WithYou-0.0.1-SNAPSHOT.jar app.jar

# 컨테이너 시작 시 실행할 명령어
ENTRYPOINT ["java","-jar","/app.jar"]