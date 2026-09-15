package com.sist.web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@Bean	// <bean>
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception
	{
		/*
		 * 	접근 권한
		 * 	로그인
		 * 	로그아웃
		 * 	자동로그인 
		 * 
		 * 	Controller
		 * 		|
		 * 	SecurityConfig => URL 별 권한 설정
		 * 		|
		 * 	CustomUserDetailsService => 사용자 정보 / 권한 정보
		 * 
		 * 	=> EL ${} th:each="vo:${list}"
		 * 					[[${vo.name}]]
		 * 	=> ${sessionScope.id}	<< JSP
		 * 		=> ${session.id}	<< thymeleaf에서는 session이라고 써서 사용
		 * 
		 * 	=> roles("ADMIN") => ROLE_ADMIN
		 * 
		 * 	authority : 권한
		 * 	springmember : enable / userid username userpwd
		 */
		// 1. 인증 => 권한 부여
		http.csrf(csrf->csrf.disable())	// 위조 => 방지
			.authorizeHttpRequests(auth->auth
						.requestMatchers("/","/login").permitAll()	// 지정된 도메인은 누구나 접근 가능하게 만든다
						.requestMatchers("/user").authenticated()	// 로그인 된 상태면 접근 가능하게 만든다
						.requestMatchers("/admin").hasRole("ADMIN")	// 지정된 도메인은 ADMIN만 접근 가능하게 만든다
						.anyRequest().permitAll()
						/*
						 * 	permitAll()		: 모든 접속자 허용
						 * 	authenticated()	: 로그인 된 상태
						 * 	hasRole("ADMIN"): ADMIN만 접근 가능
						 * 	hasRole("USER")	: USER만 접근 가능
						 */
					)
		// 2. 로그인
			.formLogin(form->form
					.loginPage("/login")
					.loginProcessingUrl("/login_process")	// SpringSecurity에서 /login POST => Controller 처리가 아니라 Security에서 인터셉트해서 처리
					.defaultSuccessUrl("/",true)
					.failureUrl("/login?error")
					.permitAll()
					)
		// 3. 로그아웃
			.logout(logout->logout
					.logoutSuccessUrl("/")
					);
		// 자동 로그인
		
		return http.build();
	}
	
	// PasswordEncoding => 암호화 => {noop} : 암호화 없이 사용하겠다
}
