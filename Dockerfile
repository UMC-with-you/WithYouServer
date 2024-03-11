# Gradle 빌드를 위한 스테이지
FROM gradle:7.4-jdk17-alpine as build

WORKDIR /app
COPY . /app

# Gradle 의존성 캐싱을 위한 준비
COPY build.gradle /app/
COPY settings.gradle /app/
# 의존성 다운로드 및 캐싱
RUN gradle --no-daemon dependencies

# Gradle 빌드 실행 (테스트 스킵)
RUN gradle build -x test --no-daemon



# Custom JRE 이미지 사용
FROM hojinida/custom-jre:tag as jre

# 환경 변수 설정
ENV JAVA_HOME=/jre
ENV PATH="$JAVA_HOME/bin:$PATH"

# 애플리케이션 디렉토리 생성
RUN mkdir /app

# 빌드된 애플리케이션과 필요한 리소스만 복사
COPY --from=build /app/build/libs/WithYou-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

# 애플리케이션 실행
CMD ["java", "-jar", "app.jar"]
