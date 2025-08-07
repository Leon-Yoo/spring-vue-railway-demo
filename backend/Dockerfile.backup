# 백엔드 Dockerfile - Railway 최적화 버전
# 더 안정적인 이미지와 빠른 빌드를 위한 설정

# 1단계: Maven 공식 이미지로 애플리케이션 빌드
FROM eclipse-temurin:17-jdk-alpine AS build

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper와 설정 파일들 복사
COPY gradlew ./
COPY gradle gradle/
COPY build.gradle settings.gradle ./

# Gradle Wrapper 실행 권한 부여
RUN chmod +x gradlew

# 의존성 다운로드 (캐시 효율성을 위해)
RUN ./gradlew dependencies --no-daemon

# 소스 코드 복사
COPY src src/

# 애플리케이션 빌드 (테스트 제외)
RUN ./gradlew build -x test --no-daemon

# 2단계: 실행을 위한 가벼운 이미지 생성
FROM eclipse-temurin:17-jre-alpine

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=build /app/build/libs/*.jar app.jar

# 포트 설정 (Railway에서 PORT 환경변수 사용)
EXPOSE ${PORT:-8080}

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
