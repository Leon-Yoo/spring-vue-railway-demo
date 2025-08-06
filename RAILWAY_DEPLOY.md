# 🚂 Railway 배포 가이드

## 📋 사전 준비 완료!

배포를 위한 모든 설정이 완료되었습니다:
- ✅ PostgreSQL 지원 추가
- ✅ 환경별 설정 파일 (dev/prod)
- ✅ Dockerfile 생성
- ✅ Railway 설정 파일
- ✅ 환경변수 기반 API URL

## 🚀 Railway 배포 단계

### 1단계: GitHub 저장소 생성 및 업로드

```bash
# 프로젝트 루트에서 실행
git init
git add .
git commit -m "Initial commit: Spring Boot + Vue.js + Database"
git branch -M main

# GitHub에서 새 저장소 생성 후 URL 복사
git remote add origin https://github.com/YOUR_USERNAME/spring-vue-demo.git
git push -u origin main
```

### 2단계: Railway 계정 생성
1. [Railway](https://railway.app) 방문
2. **Login with GitHub** 클릭
3. GitHub 계정으로 로그인

### 3단계: PostgreSQL 데이터베이스 생성
1. Railway 대시보드에서 **+ New Project** 클릭
2. **Provision PostgreSQL** 선택
3. 데이터베이스 이름: `spring-vue-db`

### 4단계: 백엔드 서비스 배포
1. **+ New** → **GitHub Repo** 클릭
2. 생성한 저장소 선택
3. **Root Directory**: `backend` 입력
4. **Variables** 탭에서 환경변수 설정:
   ```
   SPRING_PROFILES_ACTIVE=prod
   PORT=8080
   ```
5. PostgreSQL 데이터베이스와 연결:
   - **Variables** → **RAW Editor** 클릭
   - PostgreSQL 서비스에서 **Connect** 클릭
   - 자동으로 DATABASE_URL이 설정됨

### 5단계: 프론트엔드 서비스 배포
1. **+ New** → **GitHub Repo** 클릭
2. 같은 저장소 선택
3. **Root Directory**: `frontend` 입력
4. **Variables** 탭에서 환경변수 설정:
   ```
   VITE_API_BASE_URL=https://backend-서비스명.railway.app/api
   ```
   (백엔드 URL은 백엔드 배포 완료 후 확인)

### 6단계: 도메인 설정
1. 백엔드 서비스 → **Settings** → **Domains**
2. **Generate Domain** 클릭 → URL 복사
3. 프론트엔드 서비스 **Variables**에서 `VITE_API_BASE_URL` 업데이트

## 🔧 배포 확인

### 백엔드 상태 확인:
- `https://your-backend.railway.app/api/hello`
- `https://your-backend.railway.app/api/users`

### 프론트엔드 접속:
- `https://your-frontend.railway.app`

## 💡 팁

### 1. 로그 확인
- Railway 대시보드 → 서비스 선택 → **Deployments** 탭

### 2. 데이터베이스 접속
- PostgreSQL 서비스 → **Connect** 탭에서 연결 정보 확인

### 3. 비용 모니터링
- 좌측 메뉴 → **Usage** 에서 크레딧 사용량 확인

## 🚨 문제 해결

### 백엔드 배포 실패
```bash
# 로컬에서 Docker 테스트
cd backend
docker build -t spring-backend .
docker run -p 8080:8080 spring-backend
```

### 프론트엔드 CORS 오류
- 백엔드 URL이 올바른지 확인
- `@CrossOrigin(origins = "*")` 설정 확인

### 데이터베이스 연결 실패
- Railway에서 DATABASE_URL 환경변수 확인
- PostgreSQL 서비스가 실행 중인지 확인

## 🎯 예상 비용

Railway 무료 크레딧 ($5/월)으로 충분히 운영 가능:
- 백엔드: ~$3/월
- 프론트엔드: ~$1/월
- PostgreSQL: 무료

---

**🎉 배포 완료 후 실제 URL로 접속해서 테스트해보세요!**
