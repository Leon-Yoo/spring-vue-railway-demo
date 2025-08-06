package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;

/**
 * UserService 클래스
 * 
 * 서비스(Service) 계층이란?
 * - 비즈니스 로직을 처리하는 계층
 * - 컨트롤러와 리포지토리 사이에서 실제 작업을 수행
 * - 여러 리포지토리를 조합하거나 복잡한 로직을 처리할 때 사용
 * 
 * 3계층 구조:
 * 1. Controller - 사용자 요청을 받고 응답을 보냄
 * 2. Service - 실제 비즈니스 로직을 처리
 * 3. Repository - 데이터베이스와 직접 소통
 */
@Service  // 이 클래스가 서비스 계층임을 Spring에게 알려줌
public class UserService {
    
    /**
     * 의존성 주입(Dependency Injection) - 생성자 주입 방식
     * 
     * 생성자 주입의 장점:
     * - 불변성 보장 (final 키워드 사용 가능)
     * - 순환 의존성 방지
     * - 테스트 시 mock 객체 주입이 쉬움
     * - Spring에서 권장하는 방식
     */
    private final UserRepository userRepository;
    
    /**
     * 생성자
     * Spring Boot에서는 생성자가 하나만 있으면 @Autowired를 생략해도 됨
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    /**
     * 모든 사용자 조회
     * 
     * @return 전체 사용자 목록
     */
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    /**
     * ID로 사용자 조회
     * 
     * @param id 찾고자 하는 사용자의 ID
     * @return 사용자 정보 (Optional로 감싸져 있음)
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    /**
     * 이메일로 사용자 조회
     * 
     * @param email 찾고자 하는 사용자의 이메일
     * @return 사용자 정보 (Optional로 감싸져 있음)
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    /**
     * 새로운 사용자 생성
     * 
     * @param user 생성할 사용자 정보
     * @return 저장된 사용자 정보 (ID가 자동으로 부여됨)
     */
    public User createUser(User user) {
        // 이메일 중복 확인
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다: " + user.getEmail());
        }
        
        // 사용자 저장 후 결과 반환
        return userRepository.save(user);
    }
    
    /**
     * 사용자 정보 수정
     * 
     * @param id 수정할 사용자의 ID
     * @param userDetails 수정할 사용자 정보
     * @return 수정된 사용자 정보
     */
    public User updateUser(Long id, User userDetails) {
        // 먼저 해당 ID의 사용자가 존재하는지 확인
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다. ID: " + id));
        
        // 사용자 정보 업데이트
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        
        // 수정된 정보 저장
        return userRepository.save(user);
    }
    
    /**
     * 사용자 삭제
     * 
     * @param id 삭제할 사용자의 ID
     */
    public void deleteUser(Long id) {
        // 먼저 해당 ID의 사용자가 존재하는지 확인
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("사용자를 찾을 수 없습니다. ID: " + id);
        }
        
        // 사용자 삭제
        userRepository.deleteById(id);
    }
    
    /**
     * 이름으로 사용자 검색
     * 
     * @param name 검색할 이름 (부분 검색 가능)
     * @return 검색된 사용자 목록
     */
    public List<User> searchUsersByName(String name) {
        return userRepository.findByNameContaining(name);
    }
    
    /**
     * 전체 사용자 수 조회
     * 
     * @return 등록된 사용자 총 개수
     */
    public long getUserCount() {
        return userRepository.count();
    }
}
