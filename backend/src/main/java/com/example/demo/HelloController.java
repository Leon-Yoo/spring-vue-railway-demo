package com.example.demo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

/**
 * HelloController 클래스 - 데이터베이스 연동 버전
 * 
 * 컨트롤러(Controller)란?
 * - 사용자의 HTTP 요청을 받아서 처리하는 계층
 * - URL 매핑과 HTTP 메서드(GET, POST, PUT, DELETE)를 정의
 * - 프론트엔드와 백엔드 사이의 통신 창구 역할
 * 
 * REST API란?
 * - Representational State Transfer의 약자
 * - HTTP 메서드를 이용해 자원(Resource)에 대한 CRUD 작업을 수행
 * - GET: 조회, POST: 생성, PUT: 수정, DELETE: 삭제
 */
@RestController  // JSON 형태로 데이터를 반환하는 컨트롤러임을 표시
@RequestMapping("/api")  // 모든 URL 앞에 /api가 붙음
@CrossOrigin(origins = "*")  // CORS 설정: 모든 도메인에서 접근 허용
public class HelloController {
    
    /**
     * UserService 의존성 주입
     * 생성자 주입 방식 사용 (Spring Boot 권장)
     */
    private final UserService userService;
    
    public HelloController(UserService userService) {
        this.userService = userService;
    }
    
    /**
     * 기본 인사 메시지 - GET 요청 처리
     * URL: GET /api/hello
     */
    @GetMapping("/hello")
    public Map<String, String> hello() {
        Map<String, String> response = new HashMap<>();
        response.put("message", "안녕하세요! Spring Boot와 Vue.js 학습 프로젝트입니다.");
        response.put("timestamp", java.time.LocalDateTime.now().toString());
        return response;
    }
    
    /**
     * 모든 사용자 조회 - GET 요청 처리
     * URL: GET /api/users
     */
    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
    
    /**
     * ID로 특정 사용자 조회 - GET 요청 처리
     * URL: GET /api/users/{id}
     * 
     * @PathVariable: URL의 {id} 부분을 매개변수로 받아옴
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        
        // Optional 사용법: 값이 있으면 200 OK와 함께 데이터 반환, 없으면 404 Not Found
        return user.map(ResponseEntity::ok)
                  .orElse(ResponseEntity.notFound().build());
    }
    
    /**
     * 새로운 사용자 생성 - POST 요청 처리
     * URL: POST /api/users
     * 
     * @RequestBody: 요청 본문(Body)의 JSON 데이터를 User 객체로 변환
     */
    @PostMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody User user) {
        try {
            User savedUser = userService.createUser(user);
            return ResponseEntity.ok(savedUser);
        } catch (RuntimeException e) {
            // 에러가 발생하면 400 Bad Request와 에러 메시지 반환
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * 사용자 정보 수정 - PUT 요청 처리
     * URL: PUT /api/users/{id}
     */
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User user) {
        try {
            User updatedUser = userService.updateUser(id, user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * 사용자 삭제 - DELETE 요청 처리
     * URL: DELETE /api/users/{id}
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "사용자가 성공적으로 삭제되었습니다.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.badRequest().body(error);
        }
    }
    
    /**
     * 이름으로 사용자 검색 - GET 요청 처리
     * URL: GET /api/users/search?name=김철수
     * 
     * @RequestParam: URL의 쿼리 파라미터(?name=값)를 받아옴
     */
    @GetMapping("/users/search")
    public List<User> searchUsers(@RequestParam String name) {
        return userService.searchUsersByName(name);
    }
    
    /**
     * 사용자 통계 정보 - GET 요청 처리
     * URL: GET /api/users/stats
     */
    @GetMapping("/users/stats")
    public Map<String, Object> getUserStats() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalUsers", userService.getUserCount());
        stats.put("timestamp", java.time.LocalDateTime.now().toString());
        return stats;
    }
}
