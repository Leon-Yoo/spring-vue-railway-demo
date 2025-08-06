# 🚀 Spring Boot + Vue.js + H2 Database 학습 프로젝트

초보자를 위한 **Java Spring Boot 백엔드**와 **Vue.js 프론트엔드**, **H2 인메모리 데이터베이스**를 연동한 학습용 프로젝트입니다.

## 📋 프로젝트 개요

이 프로젝트는 다음과 같은 기능을 포함합니다:
- ✅ **백엔드**: Spring Boot + JPA + H2 Database
- ✅ **프론트엔드**: Vue.js 3 + Axios
- ✅ **CRUD 기능**: 사용자 생성, 조회, 수정, 삭제
- ✅ **데이터베이스**: H2 인메모리 데이터베이스 (웹 콘솔 제공)

## 🛠️ 기술 스택

### 백엔드 (Backend)
- **Java 17**
- **Spring Boot 3.2.0**
- **Spring Data JPA** - 데이터베이스 연동
- **H2 Database** - 인메모리 데이터베이스
- **Gradle 8.5** - 빌드 도구

### 프론트엔드 (Frontend)
- **Vue.js 3** - 프론트엔드 프레임워크
- **Vite** - 개발 서버 및 빌드 도구
- **Axios** - HTTP 클라이언트

## 📁 프로젝트 구조

```
javaXvue/
├── backend/                    # Spring Boot 백엔드
│   ├── src/main/java/com/example/demo/
│   │   ├── DemoApplication.java       # Spring Boot 메인 클래스
│   │   ├── HelloController.java       # REST API 컨트롤러
│   │   ├── entity/
│   │   │   └── User.java             # 사용자 엔티티
│   │   ├── repository/
│   │   │   └── UserRepository.java   # 데이터베이스 리포지토리
│   │   └── service/
│   │       └── UserService.java      # 비즈니스 로직 서비스
│   ├── src/main/resources/
│   │   └── application.properties    # 데이터베이스 설정
│   └── build.gradle                  # Gradle 의존성 설정
└── frontend/                   # Vue.js 프론트엔드
    ├── src/
    │   ├── App.vue              # 메인 Vue 컴포넌트
    │   └── main.js              # Vue 앱 진입점
    ├── package.json             # npm 의존성 설정
    └── vite.config.js           # Vite 설정 (프록시 포함)
```

## ⚡ 빠른 시작

### 1단계: 백엔드 실행

```bash
# 백엔드 디렉토리로 이동
cd backend

# Spring Boot 애플리케이션 실행
./gradlew bootRun    # macOS/Linux
gradlew.bat bootRun  # Windows
```

**백엔드가 실행되면:**
- 🌐 서버 주소: http://localhost:8080
- 📊 H2 웹 콘솔: http://localhost:8080/h2-console
  - JDBC URL: `jdbc:h2:mem:testdb`
  - 사용자명: `sa`
  - 비밀번호: (빈 칸)

### 2단계: 프론트엔드 실행

```bash
# 새 터미널에서 프론트엔드 디렉토리로 이동
cd frontend

# 의존성 설치 (처음 한 번만)
npm install

# 개발 서버 실행
npm run dev
```

**프론트엔드가 실행되면:**
- 🌐 서버 주소: http://localhost:5173

## 🎯 주요 기능

### � REST API 엔드포인트

| 메서드 | URL | 설명 |
|--------|-----|------|
| GET | `/api/hello` | 인사 메시지 |
| GET | `/api/users` | 모든 사용자 조회 |
| GET | `/api/users/{id}` | 특정 사용자 조회 |
| POST | `/api/users` | 새 사용자 생성 |
| PUT | `/api/users/{id}` | 사용자 정보 수정 |
| DELETE | `/api/users/{id}` | 사용자 삭제 |
| GET | `/api/users/search?name={name}` | 이름으로 사용자 검색 |
| GET | `/api/users/stats` | 사용자 통계 |

### 💾 데이터베이스 기능

1. **H2 인메모리 데이터베이스**
   - 별도 설치 없이 바로 사용 가능
   - 애플리케이션 재시작 시 데이터 초기화
   - 웹 콘솔로 직접 데이터 확인 가능

2. **사용자 엔티티 (User)**
   - ID (자동 증가)
   - 이름 (필수)
   - 이메일 (필수, 중복 불가)

3. **JPA 기능**
   - 자동 테이블 생성
   - 메서드 이름 기반 쿼리 생성
   - 트랜잭션 관리

## 📚 학습 포인트

### Spring Boot 백엔드
```java
// 1. 엔티티 정의 (@Entity, @Table, @Id, @Column)
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // ...
}

// 2. 리포지토리 정의 (JpaRepository 상속)
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

// 3. 서비스 레이어 (비즈니스 로직)
@Service
public class UserService {
    private final UserRepository userRepository;
    // ...
}

// 4. REST 컨트롤러 (@RestController, @RequestMapping)
@RestController
@RequestMapping("/api")
public class HelloController {
    @GetMapping("/users")
    public List<User> getAllUsers() { /* ... */ }
}
```

### Vue.js 프론트엔드
```javascript
// 1. 컴포넌트 구조 (템플릿, 스크립트, 스타일)
export default {
  data() {
    return {
      users: [],
      newUser: { name: '', email: '' }
    }
  },
  methods: {
    // 2. Axios로 HTTP 요청
    async loadUsers() {
      const response = await axios.get('/api/users')
      this.users = response.data
    }
  }
}
```

## 🔍 데이터베이스 확인하기

1. **H2 웹 콘솔 접속**: http://localhost:8080/h2-console
2. **연결 정보 입력**:
   - JDBC URL: `jdbc:h2:mem:testdb`
   - User Name: `sa`
   - Password: (빈 칸)
3. **Connect 버튼 클릭**
4. **SQL 쿼리 실행**:
   ```sql
   -- 모든 사용자 조회
   SELECT * FROM users;
   
   -- 사용자 수 조회
   SELECT COUNT(*) FROM users;
   ```

## 📖 다음 학습 단계

1. **Spring Security 추가** - 인증/인가 기능
2. **실제 데이터베이스 연동** - MySQL, PostgreSQL
3. **파일 업로드 기능** - 이미지 업로드
4. **페이징 처리** - 대량 데이터 처리
5. **검색 기능 강화** - 다양한 검색 조건
6. **Vue Router** - 여러 페이지 구성
7. **Vuex/Pinia** - 상태 관리

## 🚨 문제 해결

### 백엔드 실행 문제
- **포트 8080 사용 중**: 다른 애플리케이션이 8080 포트를 사용하고 있는지 확인
- **Java 버전 문제**: Java 17 이상이 설치되어 있는지 확인

### 프론트엔드 실행 문제
- **Node.js 버전**: Node.js 16 이상 권장
- **의존성 문제**: `npm install`로 의존성 재설치

### CORS 문제
- 백엔드에 `@CrossOrigin(origins = "*")` 설정 확인
- Vite 프록시 설정 확인

## 📞 도움말

- **H2 Database 문서**: https://h2database.com/
- **Spring Boot 가이드**: https://spring.io/guides
- **Vue.js 문서**: https://vuejs.org/guide/
- **Axios 문서**: https://axios-http.com/

---

**🎉 축하합니다! 완전한 풀스택 웹 애플리케이션을 성공적으로 구축했습니다!**
