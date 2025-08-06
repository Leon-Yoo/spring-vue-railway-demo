<!-- Vue.js 컴포넌트 파일 (.vue)의 구조:
     1. <template>: HTML 템플릿 (화면에 보이는 부분)
     2. <script>: JavaScript 로직 (데이터, 메서드 등)
     3. <style>: CSS 스타일 (컴포넌트의 디자인)
-->

<template>
  <!-- 최상위 div 요소 -->
  <div id="app">
    <!-- 페이지 제목 -->
    <header>
      <h1>🚀 Spring Boot + Vue.js 학습 프로젝트</h1>
      <p>백엔드와 프론트엔드가 성공적으로 연결되었습니다!</p>
    </header>

    <!-- 메인 콘텐츠 영역 -->
    <main>
      <!-- 백엔드 API 테스트 섹션 -->
      <section class="api-test">
        <h2>📡 백엔드 API 테스트</h2>
        
        <!-- 간단한 메시지 API 테스트 -->
        <div class="test-box">
          <h3>1. 간단한 메시지 API</h3>
          <button @click="fetchHello" :disabled="loading">
            {{ loading ? '로딩 중...' : 'Hello API 호출' }}
          </button>
          <p v-if="message" class="result">{{ message }}</p>
        </div>

        <!-- 사용자 정보 API 테스트 -->
        <div class="test-box">
          <h3>2. 사용자 정보 API (JSON)</h3>
          <button @click="fetchUser" :disabled="loading">
            {{ loading ? '로딩 중...' : 'User API 호출' }}
          </button>
          <div v-if="user" class="result">
            <p><strong>이름:</strong> {{ user.name }}</p>
            <p><strong>나이:</strong> {{ user.age }}세</p>
          </div>
        </div>

        <!-- 에러 표시 영역 -->
        <div v-if="error" class="error">
          <h3>⚠️ 오류 발생</h3>
          <p>{{ error }}</p>
          <small>백엔드 서버(localhost:8080)가 실행 중인지 확인하세요.</small>
        </div>
      </section>

      <!-- 학습 가이드 섹션 -->
      <section class="guide">
        <h2>📚 학습 가이드</h2>
        <div class="step">
          <h3>1단계: 파일 구조 이해하기</h3>
          <ul>
            <li><code>backend/src/main/java/com/example/demo/DemoApplication.java</code> - Spring Boot 시작점</li>
            <li><code>backend/src/main/java/com/example/demo/HelloController.java</code> - REST API 컨트롤러</li>
            <li><code>frontend/src/App.vue</code> - Vue.js 메인 컴포넌트 (이 파일)</li>
            <li><code>frontend/src/main.js</code> - Vue.js 앱 진입점</li>
          </ul>
        </div>
        
        <div class="step">
          <h3>2단계: API 연동 이해하기</h3>
          <ul>
            <li>Spring Boot: <code>@RestController</code>로 API 제공</li>
            <li>Vue.js: <code>axios</code>로 API 호출</li>
            <li>CORS: 다른 도메인 간 통신 허용</li>
            <li>프록시: Vite의 프록시 설정으로 개발 편의성 향상</li>
          </ul>
        </div>
      </section>
    </main>
  </div>
</template>

<script>
// axios 라이브러리를 import (HTTP 요청을 위한 라이브러리)
import axios from 'axios'

// Vue 컴포넌트의 JavaScript 부분
export default {
  name: 'App',  // 컴포넌트 이름
  
  // 데이터 함수: 컴포넌트에서 사용할 반응형 데이터 정의
  data() {
    return {
      message: '',      // 백엔드에서 받은 메시지
      user: null,       // 백엔드에서 받은 사용자 정보
      loading: false,   // 로딩 상태
      error: ''         // 에러 메시지
    }
  },
  
  // 메서드: 컴포넌트에서 사용할 함수들 정의
  methods: {
    // Hello API를 호출하는 함수
    async fetchHello() {
      this.loading = true   // 로딩 시작
      this.error = ''       // 이전 에러 메시지 제거
      
      try {
        // axios를 사용해서 백엔드 API 호출
        // Vite 프록시 설정으로 /api 요청이 localhost:8080으로 전달됨
        const response = await axios.get('/api/hello')
        this.message = response.data  // 응답 데이터를 message에 저장
      } catch (err) {
        // 에러 발생 시 에러 메시지 설정
        this.error = `API 호출 실패: ${err.message}`
        console.error('Hello API 오류:', err)
      } finally {
        this.loading = false  // 로딩 종료
      }
    },
    
    // User API를 호출하는 함수
    async fetchUser() {
      this.loading = true
      this.error = ''
      
      try {
        const response = await axios.get('/api/user')
        this.user = response.data  // JSON 응답을 user 객체에 저장
      } catch (err) {
        this.error = `API 호출 실패: ${err.message}`
        console.error('User API 오류:', err)
      } finally {
        this.loading = false
      }
    }
  },
  
  // 컴포넌트가 마운트된 후 실행되는 생명주기 메서드
  mounted() {
    console.log('Vue.js 앱이 시작되었습니다! 🎉')
    console.log('백엔드 서버가 실행 중인지 확인하세요: http://localhost:8080')
  }
}
</script>

<style>
/* 컴포넌트의 CSS 스타일 */

/* 전체 앱 스타일 */
#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
  line-height: 1.6;
}

/* 헤더 스타일 */
header {
  text-align: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 2rem;
  border-radius: 10px;
  margin-bottom: 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

header h1 {
  margin: 0;
  font-size: 2rem;
}

header p {
  margin: 0.5rem 0 0 0;
  opacity: 0.9;
}

/* 섹션 스타일 */
section {
  background: white;
  padding: 1.5rem;
  margin-bottom: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

section h2 {
  color: #333;
  border-bottom: 2px solid #667eea;
  padding-bottom: 0.5rem;
}

/* 테스트 박스 스타일 */
.test-box {
  background: #f8f9fa;
  padding: 1rem;
  margin: 1rem 0;
  border-radius: 6px;
  border-left: 4px solid #667eea;
}

.test-box h3 {
  margin-top: 0;
  color: #495057;
}

/* 버튼 스타일 */
button {
  background: #667eea;
  color: white;
  border: none;
  padding: 0.75rem 1.5rem;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background 0.3s ease;
}

button:hover:not(:disabled) {
  background: #5a6fd8;
}

button:disabled {
  background: #6c757d;
  cursor: not-allowed;
}

/* 결과 표시 스타일 */
.result {
  background: #d4edda;
  color: #155724;
  padding: 1rem;
  margin-top: 1rem;
  border-radius: 4px;
  border: 1px solid #c3e6cb;
}

/* 에러 표시 스타일 */
.error {
  background: #f8d7da;
  color: #721c24;
  padding: 1rem;
  margin-top: 1rem;
  border-radius: 4px;
  border: 1px solid #f5c6cb;
}

/* 학습 가이드 스타일 */
.step {
  margin: 1.5rem 0;
}

.step h3 {
  color: #495057;
  margin-bottom: 0.5rem;
}

.step ul {
  margin: 0.5rem 0;
  padding-left: 1.5rem;
}

.step li {
  margin-bottom: 0.5rem;
}

/* 코드 스타일 */
code {
  background: #f1f3f4;
  padding: 0.2rem 0.4rem;
  border-radius: 3px;
  font-family: 'Courier New', monospace;
  font-size: 0.9rem;
}

/* 반응형 디자인 */
@media (max-width: 600px) {
  #app {
    padding: 10px;
  }
  
  header {
    padding: 1rem;
  }
  
  header h1 {
    font-size: 1.5rem;
  }
  
  section {
    padding: 1rem;
  }
}
</style>
