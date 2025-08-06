<!-- Use this file to provide workspace-specific custom instructions to Copilot. For more details, visit https://code.visualstudio.com/docs/copilot/copilot-customization#_use-a-githubcopilotinstructionsmd-file -->

# Spring Boot + Vue.js 학습 프로젝트 Copilot 지침

이 프로젝트는 Java Spring Boot 백엔드와 Vue.js 프론트엔드를 단계별로 학습하기 위한 교육용 프로젝트입니다.

## 프로젝트 구조
- `backend/`: Java Spring Boot 애플리케이션
- `frontend/`: Vue.js 애플리케이션

## 코딩 가이드라인

### Java/Spring Boot (백엔드)
- 모든 클래스와 메서드에 한국어 주석으로 상세한 설명 추가
- Spring Boot의 기본 어노테이션 (@RestController, @GetMapping 등) 사용법 설명
- 초보자가 이해하기 쉬운 단순한 구조 유지
- 패키지 구조: `com.example.demo`

### Vue.js (프론트엔드)
- Vue 3 Composition API보다는 Options API 사용 (학습 목적)
- 모든 컴포넌트에 상세한 주석으로 Vue.js 개념 설명
- axios를 사용한 HTTP 통신 예제 포함
- 반응형 데이터와 메서드 개념을 명확히 설명

### 일반 지침
- 코드의 모든 부분에 교육적 주석 포함
- 초보자가 따라하기 쉬운 단계별 구조
- 실제 동작하는 최소한의 예제 중심
- 한국어 주석과 변수명 사용 권장

### API 설계
- RESTful API 원칙 따르기
- CORS 설정 포함
- 간단한 JSON 응답 구조
- 에러 처리 포함

코드 생성 시 위 지침을 따라 교육적이고 이해하기 쉬운 코드를 작성해 주세요.
