# 🚀 빠른 시작 가이드

## 필수 준비사항
- **Java 17 이상** 설치
- **Node.js 18 이상** 설치
- **VS Code** (권장)

## 1단계: 백엔드 실행 (Spring Boot)

새 터미널에서:
```bash
cd backend
./gradlew bootRun
```

✅ 성공 시: `http://localhost:8080/api/hello` 접속하여 메시지 확인

## 2단계: 프론트엔드 실행 (Vue.js)

새 터미널에서:
```bash
cd frontend
npm install
npm run dev
```

✅ 성공 시: `http://localhost:3000` 접속하여 Vue.js 앱 확인

## 3단계: API 연동 테스트

브라우저에서 `http://localhost:3000`에 접속하여:
1. "Hello API 호출" 버튼 클릭
2. "User API 호출" 버튼 클릭
3. 백엔드에서 데이터가 정상적으로 표시되는지 확인

## 📁 주요 파일 설명

### 백엔드 (Spring Boot)
- `backend/src/main/java/com/example/demo/DemoApplication.java` - 애플리케이션 시작점
- `backend/src/main/java/com/example/demo/HelloController.java` - REST API 컨트롤러
- `backend/src/main/resources/application.properties` - 설정 파일
- `backend/build.gradle` - 의존성 관리

### 프론트엔드 (Vue.js)
- `frontend/src/App.vue` - 메인 Vue 컴포넌트
- `frontend/src/main.js` - Vue 앱 진입점
- `frontend/vite.config.js` - Vite 설정 (개발 서버, 프록시)
- `frontend/package.json` - npm 패키지 관리

## 🔧 문제 해결

### 포트 충돌
- 백엔드: `application.properties`에서 `server.port=8081` 변경
- 프론트엔드: `vite.config.js`에서 포트 변경

### CORS 에러
- `HelloController.java`에 `@CrossOrigin(origins = "*")` 추가됨

### Gradle 권한 문제 (Mac/Linux)
```bash
chmod +x gradlew
```

## 📚 다음 학습 단계

1. **데이터베이스 추가**: H2, MySQL 연동
2. **JPA 사용**: 데이터베이스 엔티티 생성
3. **Vue 라우터**: 페이지 라우팅 추가
4. **컴포넌트 분리**: 재사용 가능한 컴포넌트 만들기
5. **상태 관리**: Pinia 또는 Vuex 추가
6. **인증/보안**: Spring Security 적용

## 🎯 학습 목표

이 프로젝트를 통해 다음을 학습할 수 있습니다:

### Spring Boot
- Spring Boot 프로젝트 구조
- REST API 컨트롤러 작성
- JSON 응답 처리
- CORS 설정
- Gradle 빌드 도구 사용

### Vue.js
- Vue 3 기본 구조
- 컴포넌트 작성 방법
- 반응형 데이터 사용
- HTTP 요청 (axios)
- 개발 서버 설정 (Vite)

### 풀스택 개발
- 백엔드-프론트엔드 연동
- API 설계 및 사용
- 개발 환경 구성
- 프록시 설정

좋은 학습 되세요! 🎉
