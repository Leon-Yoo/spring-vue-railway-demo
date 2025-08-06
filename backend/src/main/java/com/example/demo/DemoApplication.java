package com.example.demo;

// Spring Boot의 핵심 어노테이션들을 import
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// @SpringBootApplication 어노테이션의 의미:
// 1. @Configuration: 이 클래스가 설정 클래스임을 나타냄
// 2. @EnableAutoConfiguration: Spring Boot의 자동 설정 기능 활성화
// 3. @ComponentScan: 현재 패키지부터 하위 패키지까지 컴포넌트를 자동으로 찾아서 등록
@SpringBootApplication
public class DemoApplication {

    // 프로그램의 시작점 (main 메서드)
    // Spring Boot 애플리케이션을 실행시키는 메서드
    public static void main(String[] args) {
        // SpringApplication.run()을 호출하여 Spring Boot 애플리케이션 시작
        // 첫 번째 파라미터: 메인 설정 클래스 (이 클래스 자체)
        // 두 번째 파라미터: 명령행 인수들
        SpringApplication.run(DemoApplication.class, args);
        
        // 이 메서드가 실행되면:
        // 1. 내장 Tomcat 서버가 시작됨 (기본적으로 8080 포트)
        // 2. Spring Container가 초기화됨
        // 3. 컴포넌트들이 자동으로 스캔되고 등록됨
        // 4. 애플리케이션이 HTTP 요청을 받을 준비가 됨
    }
}
