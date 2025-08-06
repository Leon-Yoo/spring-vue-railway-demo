package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

/**
 * UserRepository 인터페이스
 * 
 * 리포지토리(Repository)란?
 * - 데이터베이스와 직접 소통하는 계층
 * - 데이터를 저장, 조회, 수정, 삭제하는 기능을 제공
 * 
 * JpaRepository 상속 설명:
 * - JpaRepository<User, Long>에서 User는 엔티티 타입, Long은 기본키 타입
 * - 기본적인 CRUD 메서드들을 자동으로 제공받음
 *   * save() - 저장
 *   * findById() - ID로 조회
 *   * findAll() - 전체 조회
 *   * deleteById() - ID로 삭제
 *   * count() - 개수 조회 등
 */
@Repository  // 이 클래스가 리포지토리 계층임을 Spring에게 알려줌
public interface UserRepository extends JpaRepository<User, Long> {
    
    /**
     * 이메일로 사용자 찾기
     * 
     * 메서드 이름 규칙:
     * - findBy + 필드명: 해당 필드로 검색
     * - Spring Data JPA가 메서드 이름을 보고 자동으로 SQL을 생성해줌
     * 
     * Optional이란?
     * - 값이 있을 수도 없을 수도 있음을 나타내는 Java 8의 기능
 * - 결과가 없을 때 null 대신 Optional.empty()를 반환
     * - NullPointerException을 방지하는데 도움됨
     */
    Optional<User> findByEmail(String email);
    
    /**
     * 이름으로 사용자 찾기 (이름이 포함된 사용자들)
     * 
     * Containing: SQL의 LIKE '%name%'와 같은 기능
     * 예: "김"으로 검색하면 "김철수", "김영희" 등이 모두 검색됨
     */
    java.util.List<User> findByNameContaining(String name);
    
    /**
     * 이메일 존재 여부 확인
     * 
     * existsBy + 필드명: 해당 조건의 데이터가 존재하는지 true/false로 반환
     * 회원가입할 때 이메일 중복 확인에 사용할 수 있음
     */
    boolean existsByEmail(String email);
}
