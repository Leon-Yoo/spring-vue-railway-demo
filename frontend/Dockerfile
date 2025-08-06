# 프론트엔드 Dockerfile
# 이 파일은 Vue.js 애플리케이션을 정적 파일로 빌드하고 Nginx로 서빙하기 위한 설정입니다

# 1단계: Node.js를 사용해서 Vue.js 애플리케이션 빌드
FROM node:18-alpine AS build

# 작업 디렉토리 설정
WORKDIR /app

# package.json과 package-lock.json 복사 (캐시 효율성을 위해)
COPY package*.json ./

# 의존성 설치
RUN npm ci

# 소스 코드 복사
COPY . .

# 프로덕션용 빌드 실행
RUN npm run build

# 2단계: Nginx를 사용해서 정적 파일 서빙
FROM nginx:alpine

# 빌드된 파일을 Nginx 웹 루트로 복사
COPY --from=build /app/dist /usr/share/nginx/html

# Nginx 설정 파일 복사 (Vue Router를 위한 설정)
COPY nginx.conf /etc/nginx/nginx.conf

# 포트 80 노출
EXPOSE 80

# Nginx 실행
CMD ["nginx", "-g", "daemon off;"]
