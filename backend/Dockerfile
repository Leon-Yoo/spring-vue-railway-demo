# 백엔드 Dockerfile
# 이 파일은 Spring Boot 애플리케이션을 Docker 컨테이너로 패키징하기 위한 설정입니다

# 1단계: Gradle을 사용해서 애플리케이션 빌드
FROM gradle:8.5-jdk17 AS build

# 작업 디렉토리 설정
WORKDIR /app

# Gradle 설정 파일들 먼저 복사 (캐시 효율성을 위해)
COPY build.gradle settings.gradle ./
COPY gradle gradle

# 의존성 다운로드 (이 단계는 캐시될 수 있음)
RUN gradle dependencies --no-daemon

# 소스 코드 복사
COPY src src

# 애플리케이션 빌드 (테스트 제외)
RUN gradle build -x test --no-daemon

# 2단계: 실행을 위한 가벼운 이미지 생성
FROM openjdk:17-jre-slim

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 8080 노출
EXPOSE 8080

# 애플리케이션 실행
# SPRING_PROFILES_ACTIVE 환경변수로 프로파일 설정 가능
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
