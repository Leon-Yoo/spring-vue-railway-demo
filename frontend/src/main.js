// Vue.js 애플리케이션의 진입점 파일
// 이 파일에서 Vue 앱을 생성하고 HTML에 마운트합니다

// Vue의 createApp 함수를 import (Vue 3 방식)
import { createApp } from 'vue'
// 메인 App 컴포넌트를 import
import App from './App.vue'

// Vue 애플리케이션 생성
// createApp() 함수에 App 컴포넌트를 전달하여 Vue 앱 인스턴스 생성
const app = createApp(App)

// 생성된 Vue 앱을 HTML의 id="app" 요소에 마운트
// index.html의 <div id="app"></div>에 Vue 앱이 렌더링됩니다
app.mount('#app')

// 실행 순서:
// 1. main.js가 실행됨 (index.html에서 로드)
// 2. App.vue 컴포넌트가 로드됨
// 3. Vue 앱이 생성되고 #app 요소에 마운트됨
// 4. App.vue의 내용이 화면에 표시됨
