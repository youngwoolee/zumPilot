package com.zum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
@SpringBootApplication =
	@Configuration + 현재의 클래스가 Spring의 설정 파일임을 어플리케이션 컨텍스트에 알려줌
	@EnableAutoConfiguration + Spring Boot 가 ClassPath 세팅, 다른 Bean을 추가하도록 함
	@ComponentScan 해당위치부터 다른 컴포넌트, 설정, 서비스를 찾도록 함

*/

@SpringBootApplication
public class PilotApplication {

	public static void main(String[] args) {
		SpringApplication.run(PilotApplication.class, args);
	}
}
