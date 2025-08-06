# 🚀 데이터베이스 연동 완료! 빠른 시작 가이드

## 1단계: 백엔드 실행 (Spring Boot + H2 Database)

### VS Code에서 실행하는 방법
1. `Ctrl + Shift + P` → "Tasks: Run Task" 입력
2. "데이터베이스 백엔드 실행" 선택
3. 터미널에서 "Started DemoApplication" 메시지 확인

### 수동으로 실행하는 방법
```bash
cd backend
gradlew.bat bootRun
```

✅ **백엔드 실행 완료 확인**
- 서버 주소: http://localhost:8080
- API 테스트: http://localhost:8080/api/hello
- H2 데이터베이스 콘솔: http://localhost:8080/h2-console

## 2단계: 프론트엔드 실행 (Vue.js)

### VS Code에서 실행하는 방법
1. `Ctrl + Shift + P` → "Tasks: Run Task" 입력
2. "프론트엔드 실행" 선택

### 수동으로 실행하는 방법
```bash
cd frontend
npm install  # 처음 한 번만 실행
npm run dev
```

✅ **프론트엔드 실행 완료 확인**
- 웹 애플리케이션: http://localhost:5173

## 3단계: 데이터베이스 기능 테스트

### 웹 애플리케이션에서 테스트
1. http://localhost:5173 접속
2. "새 사용자 추가" 폼에서 이름과 이메일 입력
3. "사용자 추가" 버튼 클릭
4. 사용자 목록에서 새로 추가된 사용자 확인
5. 수정/삭제 버튼으로 CRUD 기능 테스트

### H2 데이터베이스에서 직접 확인
1. http://localhost:8080/h2-console 접속
2. 연결 정보 입력:
   - **JDBC URL**: `jdbc:h2:mem:testdb`
   - **User Name**: `sa`
   - **Password**: (빈 칸)
3. "Connect" 버튼 클릭
4. SQL 쿼리 실행:
   ```sql
   SELECT * FROM users;
   SELECT COUNT(*) FROM users;
   ```

## 📊 사용 가능한 API 목록

| 방법 | URL | 설명 |
|------|-----|------|
| GET | `/api/hello` | 인사 메시지 |
| GET | `/api/users` | 모든 사용자 조회 |
| GET | `/api/users/{id}` | 특정 사용자 조회 |
| POST | `/api/users` | 새 사용자 생성 |
| PUT | `/api/users/{id}` | 사용자 정보 수정 |
| DELETE | `/api/users/{id}` | 사용자 삭제 |
| GET | `/api/users/search?name={name}` | 이름으로 검색 |
| GET | `/api/users/stats` | 사용자 통계 |

## 🎯 학습한 내용

### ✅ 백엔드 (Spring Boot)
- **JPA 엔티티**: `@Entity`, `@Table`, `@Id`, `@Column`
- **레포지토리**: `JpaRepository` 상속으로 자동 CRUD
- **서비스 계층**: 비즈니스 로직 분리
- **REST 컨트롤러**: `@RestController`, `@GetMapping`, `@PostMapping` 등
- **의존성 주입**: 생성자 주입 방식

### ✅ 프론트엔드 (Vue.js)
- **Vue 3 Options API**: `data()`, `methods`, `mounted`
- **HTTP 통신**: Axios로 REST API 호출
- **반응형 데이터**: 데이터 변경 시 자동 화면 업데이트
- **이벤트 처리**: `@click`, `v-model`, `v-if`, `v-for`

### ✅ 데이터베이스 (H2)
- **인메모리 데이터베이스**: 별도 설치 없이 사용
- **웹 콘솔**: 브라우저에서 데이터 직접 확인
- **JPA 자동 생성**: 엔티티에서 테이블 자동 생성
- **CRUD 작업**: 생성, 조회, 수정, 삭제

## 🔧 문제 해결

### 백엔드가 실행되지 않는 경우
```bash
# Java 버전 확인 (17 이상 필요)
java -version

# Gradle 캐시 정리
cd backend
rmdir /s .gradle
gradlew.bat clean
gradlew.bat bootRun
```

### 프론트엔드가 실행되지 않는 경우
```bash
# Node.js 버전 확인 (16 이상 권장)
node -v

# 의존성 재설치
cd frontend
rmdir /s node_modules
npm install
npm run dev
```

### 데이터베이스 연결 문제
- H2 콘솔에서 JDBC URL이 정확한지 확인: `jdbc:h2:mem:testdb`
- 백엔드가 먼저 실행된 상태에서 H2 콘솔 접속

## 🎉 축하합니다!

성공적으로 **Spring Boot + Vue.js + H2 Database**를 연동한 완전한 풀스택 웹 애플리케이션을 구축했습니다!

이제 다음 단계로 넘어가서 더 복잡한 기능들을 추가해보세요:
- 사용자 검증 기능 추가
- 페이지네이션 구현
- 파일 업로드 기능
- 사용자 인증 시스템 (Spring Security)
- 실제 데이터베이스 연동 (MySQL, PostgreSQL)
