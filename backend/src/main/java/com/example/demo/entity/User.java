package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * User 엔티티 클래스
 * 
 * 엔티티(Entity)란?
 * - 데이터베이스의 테이블과 매핑되는 Java 클래스
 * - 이 클래스는 users 테이블과 연결됩니다
 * 
 * JPA 어노테이션 설명:
 * @Entity - 이 클래스가 데이터베이스 테이블과 매핑됨을 선언
 * @Table - 실제 데이터베이스 테이블 이름 지정
 * @Id - 기본키(Primary Key)를 나타냄
 * @GeneratedValue - 기본키 값을 자동으로 생성
 * @Column - 테이블의 컬럼과 매핑
 */
@Entity
@Table(name = "users")  // 데이터베이스에서 테이블 이름은 "users"
public class User {
    
    /**
     * 사용자 ID (기본키)
     * GenerationType.IDENTITY는 데이터베이스가 자동으로 ID를 증가시켜줌
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    /**
     * 사용자 이름
     * nullable = false는 이 필드가 반드시 값을 가져야 함을 의미
     */
    @Column(nullable = false)
    private String name;
    
    /**
     * 사용자 이메일
     * unique = true는 이메일이 중복될 수 없음을 의미
     */
    @Column(nullable = false, unique = true)
    private String email;
    
    /**
     * 기본 생성자
     * JPA에서는 반드시 기본 생성자가 필요합니다
     */
    public User() {
    }
    
    /**
     * 매개변수가 있는 생성자
     * 새로운 사용자를 만들 때 사용
     */
    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }
    
    // Getter와 Setter 메서드들
    // 이 메서드들을 통해 private 필드에 접근할 수 있습니다
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * toString 메서드
     * 객체를 문자열로 표현할 때 사용 (디버깅에 유용)
     */
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
