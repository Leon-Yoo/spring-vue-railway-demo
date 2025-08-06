// Vite 설정 파일
// Vite는 Vue.js 개발을 위한 빠른 빌드 도구입니다
import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

// Vite 설정을 내보냅니다
export default defineConfig({
  // Vue.js 플러그인 사용
  plugins: [vue()],
  
  // 빌드 설정
  build: {
    outDir: 'dist',  // 빌드 결과물이 저장될 디렉토리
    sourcemap: false, // 프로덕션에서는 소스맵 비활성화
    rollupOptions: {
      output: {
        // 청크 파일명 설정
        chunkFileNames: 'assets/[name]-[hash].js',
        entryFileNames: 'assets/[name]-[hash].js',
        assetFileNames: 'assets/[name]-[hash].[ext]'
      }
    }
  },
  
  // 개발 서버 설정
  server: {
    port: 3000,  // 프론트엔드는 3000 포트에서 실행
    
    // 프록시 설정: /api로 시작하는 요청을 백엔드로 전달 (개발환경에서만)
    proxy: {
      '/api': {
        target: 'http://localhost:8080',  // Spring Boot 서버 주소
        changeOrigin: true,               // CORS 문제 해결
        // rewrite: (path) => path  // 경로를 그대로 유지
      }
    }
  },
  
  // 환경변수 설정
  define: {
    // 프로덕션에서 사용할 API 베이스 URL
    __API_BASE_URL__: JSON.stringify(process.env.VITE_API_BASE_URL || '/api')
  }
})
