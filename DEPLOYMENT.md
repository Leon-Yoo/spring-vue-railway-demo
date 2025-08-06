# 🚀 Railway를 사용한 배포 가이드

Railway는 가장 쉽고 저렴한 배포 플랫폼 중 하나입니다. GitHub와 연동하여 자동 배포가 가능합니다.

## 📋 사전 준비

### 1. GitHub 저장소 생성
1. [GitHub](https://github.com)에서 새 저장소 생성
2. 프로젝트 코드를 GitHub에 푸시

```bash
# 프로젝트 루트에서 실행
git init
git add .
git commit -m "Initial commit"
git remote add origin https://github.com/your-username/spring-vue-demo.git
git push -u origin main
```

### 2. Railway 계정 생성
1. [Railway](https://railway.app)에서 GitHub 계정으로 회원가입
2. 매월 $5 크레딧 무료 제공

## 🚂 Railway 배포 과정

### 1단계: 백엔드 (Spring Boot) 배포

1. **New Project** 클릭
2. **Deploy from GitHub repo** 선택
3. 생성한 저장소 선택
4. **Add variables** 클릭하여 환경변수 설정:
   ```
   SPRING_PROFILES_ACTIVE=prod
   PORT=8080
   ```

### 2단계: PostgreSQL 데이터베이스 추가

1. 프로젝트 대시보드에서 **+ New** 클릭
2. **Database** → **PostgreSQL** 선택
3. 데이터베이스가 생성되면 **Variables** 탭에서 연결 정보 확인
4. 백엔드 서비스의 **Variables**에 데이터베이스 정보 추가:
   ```
   DATABASE_URL=postgresql://username:password@hostname:port/database
   DB_USERNAME=postgres
   DB_PASSWORD=your-password
   ```

### 3단계: 프론트엔드 (Vue.js) 배포

1. **+ New** → **GitHub Repo** → 같은 저장소 선택
2. **Root Directory**를 `frontend`로 설정
3. **Environment Variables** 설정:
   ```
   VITE_API_BASE_URL=https://your-backend-url.railway.app/api
   ```

### 4단계: 도메인 설정

1. 각 서비스의 **Settings** → **Domains**
2. **Generate Domain** 클릭하여 공개 URL 생성
3. 백엔드 URL을 프론트엔드의 `VITE_API_BASE_URL`에 업데이트

## 🛠️ 배포 최적화

### 백엔드 최적화
`backend/railway.json` 파일 생성:
```json
{
  "build": {
    "builder": "DOCKERFILE",
    "dockerfilePath": "Dockerfile"
  },
  "deploy": {
    "restartPolicyType": "ON_FAILURE",
    "restartPolicyMaxRetries": 10
  }
}
```

### 프론트엔드 최적화
`frontend/railway.json` 파일 생성:
```json
{
  "build": {
    "builder": "DOCKERFILE",
    "dockerfilePath": "Dockerfile"
  },
  "deploy": {
    "restartPolicyType": "ON_FAILURE"
  }
}
```

## 💰 비용 예상

### Railway 무료 크레딧으로 가능한 사용량:
- **무료 크레딧**: 매월 $5
- **백엔드 서비스**: ~$3-4/월 (512MB RAM)
- **PostgreSQL**: 무료
- **프론트엔드**: ~$1-2/월 (정적 호스팅)

**총 예상 비용**: 무료 크레딧 내에서 충분히 운영 가능!

## 🔧 문제 해결

### 일반적인 문제들:

1. **빌드 실패**
   - `Dockerfile`이 올바른 위치에 있는지 확인
   - 환경변수가 올바르게 설정되었는지 확인

2. **데이터베이스 연결 실패**
   - `DATABASE_URL` 형식 확인
   - PostgreSQL 서비스가 실행 중인지 확인

3. **CORS 오류**
   - 백엔드의 `@CrossOrigin` 설정 확인
   - 프론트엔드의 API URL이 올바른지 확인

### 로그 확인 방법:
1. Railway 대시보드에서 서비스 클릭
2. **Deployments** 탭에서 로그 확인

## 🎯 배포 완료 체크리스트

- [ ] GitHub 저장소에 코드 푸시 완료
- [ ] Railway에서 백엔드 서비스 배포 완료
- [ ] PostgreSQL 데이터베이스 연결 완료
- [ ] 프론트엔드 서비스 배포 완료
- [ ] 도메인 설정 완료
- [ ] API 연동 테스트 완료
- [ ] 사용자 CRUD 기능 테스트 완료

## 🌐 다른 배포 옵션들

### 무료 대안:
1. **Render** - 무료 계정으로 시작 가능
2. **Fly.io** - 소규모 앱 무료 호스팅
3. **Vercel + PlanetScale** - 프론트엔드 특화

### 저렴한 유료 옵션:
1. **DigitalOcean App Platform** - 월 $5
2. **AWS Lightsail** - 월 $3.50
3. **Google Cloud Run** - 사용량 기반 과금

---

**🎉 축하합니다! 이제 전 세계 어디서나 접근 가능한 웹 애플리케이션을 만들었습니다!**
