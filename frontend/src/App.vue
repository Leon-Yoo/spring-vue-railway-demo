<!-- Vue.js 템플릿 부분 (HTML과 비슷) -->
<template>
  <div id="app">
    <!-- 페이지 헤더 -->
    <header class="header">
      <h1>🚀 Spring Boot + Vue.js 학습 프로젝트</h1>
      <p>데이터베이스 연동 완료! H2 Database를 사용하고 있습니다.</p>
    </header>

    <!-- 메인 컨텐츠 영역 -->
    <main class="main-content">
      
      <!-- API 테스트 섹션 -->
      <section class="api-section">
        <h2>📡 API 테스트</h2>
        <div class="button-group">
          <button @click="testHello" class="btn btn-primary">
            인사 메시지 가져오기
          </button>
        </div>
        
        <!-- API 응답 표시 영역 -->
        <div v-if="apiResponse" class="response-box">
          <h3>🔥 API 응답:</h3>
          <pre>{{ JSON.stringify(apiResponse, null, 2) }}</pre>
        </div>
      </section>

      <!-- 사용자 관리 섹션 -->
      <section class="user-section">
        <h2>👥 사용자 관리 (데이터베이스 연동)</h2>
        
        <!-- 새 사용자 추가 폼 -->
        <div class="add-user-form">
          <h3>새 사용자 추가</h3>
          <div class="form-group">
            <input 
              v-model="newUser.name" 
              type="text" 
              placeholder="이름을 입력하세요"
              class="input-field"
            >
            <input 
              v-model="newUser.email" 
              type="email" 
              placeholder="이메일을 입력하세요"
              class="input-field"
            >
            <button @click="addUser" class="btn btn-success">
              사용자 추가
            </button>
          </div>
        </div>

        <!-- 사용자 목록 -->
        <div class="user-list">
          <div class="user-list-header">
            <h3>사용자 목록</h3>
            <button @click="loadUsers" class="btn btn-secondary">
              새로고침
            </button>
          </div>
          
          <!-- 사용자가 없을 때 -->
          <div v-if="users.length === 0" class="no-users">
            <p>등록된 사용자가 없습니다. 위에서 새 사용자를 추가해보세요!</p>
          </div>
          
          <!-- 사용자 목록 표시 -->
          <div v-else class="users-grid">
            <div 
              v-for="user in users" 
              :key="user.id" 
              class="user-card"
            >
              <div class="user-info">
                <h4>{{ user.name }}</h4>
                <p>{{ user.email }}</p>
                <small>ID: {{ user.id }}</small>
              </div>
              <div class="user-actions">
                <button 
                  @click="editUser(user)" 
                  class="btn btn-warning btn-sm"
                >
                  수정
                </button>
                <button 
                  @click="deleteUser(user.id)" 
                  class="btn btn-danger btn-sm"
                >
                  삭제
                </button>
              </div>
            </div>
          </div>
        </div>

        <!-- 통계 정보 -->
        <div class="stats-section">
          <h3>📊 통계</h3>
          <p>총 사용자 수: <strong>{{ userStats.totalUsers || 0 }}</strong></p>
          <button @click="loadStats" class="btn btn-info">
            통계 새로고침
          </button>
        </div>
      </section>

      <!-- 에러/성공 메시지 표시 -->
      <div v-if="message" :class="['message', messageType]">
        {{ message }}
      </div>
    </main>
  </div>
</template>

<script>
// Vue.js 스크립트 부분 (JavaScript)
import axios from 'axios'

export default {
  name: 'App',  // 컴포넌트 이름
  
  // data(): Vue.js의 반응형 데이터를 정의
  // 이 데이터들이 변경되면 화면이 자동으로 업데이트됨
  data() {
    return {
      // API 베이스 URL - 환경변수에서 가져옴
      apiBaseUrl: import.meta.env.VITE_API_BASE_URL || '/api',
      
      // API 응답을 저장할 변수
      apiResponse: null,
      
      // 사용자 목록을 저장할 배열
      users: [],
      
      // 새 사용자 입력 폼 데이터
      newUser: {
        name: '',
        email: ''
      },
      
      // 사용자 통계 정보
      userStats: {},
      
      // 메시지 표시용
      message: '',
      messageType: 'success'  // 'success', 'error', 'info'
    }
  },
  
  // mounted(): 컴포넌트가 DOM에 마운트된 후 실행되는 라이프사이클 훅
  // 페이지가 로드되자마자 사용자 목록과 통계를 가져옴
  mounted() {
    this.loadUsers()
    this.loadStats()
  },
  
  // methods: 컴포넌트에서 사용할 메서드들을 정의
  methods: {
    /**
     * Hello API 테스트
     * axios를 사용해서 백엔드의 /api/hello 엔드포인트 호출
     */
    async testHello() {
      try {
        // 환경변수 기반 API URL 사용
        const response = await axios.get(`${this.apiBaseUrl}/hello`)
        
        // 응답 데이터를 화면에 표시하기 위해 저장
        this.apiResponse = response.data
        this.showMessage('API 호출 성공!', 'success')
      } catch (error) {
        console.error('API 호출 실패:', error)
        this.showMessage('API 호출에 실패했습니다.', 'error')
      }
    },

    /**
     * 모든 사용자 목록 가져오기
     */
    async loadUsers() {
      try {
        const response = await axios.get(`${this.apiBaseUrl}/users`)
        this.users = response.data
      } catch (error) {
        console.error('사용자 목록 로드 실패:', error)
        this.showMessage('사용자 목록을 불러오는데 실패했습니다.', 'error')
      }
    },

    /**
     * 새 사용자 추가
     */
    async addUser() {
      // 입력값 검증
      if (!this.newUser.name || !this.newUser.email) {
        this.showMessage('이름과 이메일을 모두 입력해주세요.', 'error')
        return
      }

      try {
        // POST 요청으로 새 사용자 생성
        await axios.post(`${this.apiBaseUrl}/users`, this.newUser)
        
        // 폼 초기화
        this.newUser = { name: '', email: '' }
        
        // 사용자 목록과 통계 새로고침
        this.loadUsers()
        this.loadStats()
        
        this.showMessage('사용자가 성공적으로 추가되었습니다!', 'success')
      } catch (error) {
        console.error('사용자 추가 실패:', error)
        if (error.response && error.response.data && error.response.data.error) {
          this.showMessage(error.response.data.error, 'error')
        } else {
          this.showMessage('사용자 추가에 실패했습니다.', 'error')
        }
      }
    },

    /**
     * 사용자 삭제
     */
    async deleteUser(userId) {
      if (!confirm('정말로 이 사용자를 삭제하시겠습니까?')) {
        return
      }

      try {
        await axios.delete(`${this.apiBaseUrl}/users/${userId}`)
        
        // 사용자 목록과 통계 새로고침
        this.loadUsers()
        this.loadStats()
        
        this.showMessage('사용자가 성공적으로 삭제되었습니다.', 'success')
      } catch (error) {
        console.error('사용자 삭제 실패:', error)
        this.showMessage('사용자 삭제에 실패했습니다.', 'error')
      }
    },

    /**
     * 사용자 수정 (간단한 버전)
     */
    editUser(user) {
      const newName = prompt('새로운 이름을 입력하세요:', user.name)
      const newEmail = prompt('새로운 이메일을 입력하세요:', user.email)
      
      if (newName && newEmail) {
        this.updateUser(user.id, { name: newName, email: newEmail })
      }
    },

    /**
     * 사용자 정보 업데이트
     */
    async updateUser(userId, userData) {
      try {
        await axios.put(`${this.apiBaseUrl}/users/${userId}`, userData)
        
        // 사용자 목록 새로고침
        this.loadUsers()
        
        this.showMessage('사용자 정보가 성공적으로 수정되었습니다.', 'success')
      } catch (error) {
        console.error('사용자 수정 실패:', error)
        if (error.response && error.response.data && error.response.data.error) {
          this.showMessage(error.response.data.error, 'error')
        } else {
          this.showMessage('사용자 수정에 실패했습니다.', 'error')
        }
      }
    },

    /**
     * 사용자 통계 가져오기
     */
    async loadStats() {
      try {
        const response = await axios.get(`${this.apiBaseUrl}/users/stats`)
        this.userStats = response.data
      } catch (error) {
        console.error('통계 로드 실패:', error)
      }
    },

    /**
     * 메시지 표시
     */
    showMessage(text, type = 'info') {
      this.message = text
      this.messageType = type
      
      // 3초 후 메시지 자동 삭제
      setTimeout(() => {
        this.message = ''
      }, 3000)
    }
  }
}
</script>

<!-- 스타일 부분 (CSS) -->
<style>
/* 기본 스타일 */
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

body {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  background-color: #f5f5f5;
}

#app {
  max-width: 1200px;
  margin: 0 auto;
  padding: 20px;
}

/* 헤더 스타일 */
.header {
  text-align: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 2rem;
  border-radius: 10px;
  margin-bottom: 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.header h1 {
  font-size: 2.5rem;
  margin-bottom: 0.5rem;
}

/* 메인 컨텐츠 */
.main-content {
  display: grid;
  gap: 2rem;
}

/* 섹션 스타일 */
.api-section, .user-section {
  background: white;
  padding: 2rem;
  border-radius: 10px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.api-section h2, .user-section h2 {
  color: #333;
  margin-bottom: 1rem;
  border-bottom: 2px solid #667eea;
  padding-bottom: 0.5rem;
}

/* 버튼 스타일 */
.btn {
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 14px;
  transition: all 0.3s ease;
  margin: 5px;
}

.btn-primary {
  background-color: #667eea;
  color: white;
}

.btn-primary:hover {
  background-color: #5a6fd8;
}

.btn-success {
  background-color: #28a745;
  color: white;
}

.btn-success:hover {
  background-color: #218838;
}

.btn-secondary {
  background-color: #6c757d;
  color: white;
}

.btn-secondary:hover {
  background-color: #545b62;
}

.btn-warning {
  background-color: #ffc107;
  color: #212529;
}

.btn-warning:hover {
  background-color: #e0a800;
}

.btn-danger {
  background-color: #dc3545;
  color: white;
}

.btn-danger:hover {
  background-color: #c82333;
}

.btn-info {
  background-color: #17a2b8;
  color: white;
}

.btn-info:hover {
  background-color: #138496;
}

.btn-sm {
  padding: 5px 10px;
  font-size: 12px;
}

/* 응답 박스 */
.response-box {
  background-color: #f8f9fa;
  border: 1px solid #dee2e6;
  border-radius: 5px;
  padding: 1rem;
  margin-top: 1rem;
}

.response-box pre {
  background-color: #e9ecef;
  padding: 10px;
  border-radius: 3px;
  overflow-x: auto;
}

/* 폼 스타일 */
.add-user-form {
  background-color: #f8f9fa;
  padding: 1.5rem;
  border-radius: 8px;
  margin-bottom: 2rem;
}

.form-group {
  display: flex;
  gap: 10px;
  flex-wrap: wrap;
  align-items: center;
}

.input-field {
  padding: 10px;
  border: 1px solid #ced4da;
  border-radius: 5px;
  font-size: 14px;
  flex: 1;
  min-width: 200px;
}

.input-field:focus {
  outline: none;
  border-color: #667eea;
  box-shadow: 0 0 0 2px rgba(102, 126, 234, 0.25);
}

/* 사용자 목록 */
.user-list-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 1rem;
}

.no-users {
  text-align: center;
  padding: 2rem;
  color: #6c757d;
  font-style: italic;
}

.users-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 1rem;
}

.user-card {
  border: 1px solid #dee2e6;
  border-radius: 8px;
  padding: 1rem;
  background-color: #fff;
  transition: transform 0.2s ease, box-shadow 0.2s ease;
}

.user-card:hover {
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.15);
}

.user-info h4 {
  color: #333;
  margin-bottom: 0.5rem;
}

.user-info p {
  color: #666;
  margin-bottom: 0.5rem;
}

.user-info small {
  color: #999;
}

.user-actions {
  margin-top: 1rem;
  display: flex;
  gap: 0.5rem;
}

/* 통계 섹션 */
.stats-section {
  background-color: #e3f2fd;
  padding: 1.5rem;
  border-radius: 8px;
  margin-top: 2rem;
}

.stats-section h3 {
  color: #1976d2;
  margin-bottom: 1rem;
}

/* 메시지 스타일 */
.message {
  position: fixed;
  top: 20px;
  right: 20px;
  padding: 1rem 1.5rem;
  border-radius: 5px;
  color: white;
  font-weight: bold;
  z-index: 1000;
  animation: slideIn 0.3s ease;
}

.message.success {
  background-color: #28a745;
}

.message.error {
  background-color: #dc3545;
}

.message.info {
  background-color: #17a2b8;
}

@keyframes slideIn {
  from {
    transform: translateX(100%);
    opacity: 0;
  }
  to {
    transform: translateX(0);
    opacity: 1;
  }
}

/* 반응형 디자인 */
@media (max-width: 768px) {
  .header h1 {
    font-size: 1.8rem;
  }
  
  .form-group {
    flex-direction: column;
  }
  
  .input-field {
    min-width: 100%;
  }
  
  .users-grid {
    grid-template-columns: 1fr;
  }
  
  .user-list-header {
    flex-direction: column;
    gap: 1rem;
  }
}
</style>
