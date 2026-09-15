package com.sist.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.sist.web.jwt.JwtAuthenticationFilter;
import com.sist.web.jwt.JwtAuthenticationProvider;
import com.sist.web.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class JwtSecurityConfig 
{
	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter(CustomUserDetailsService uds, JwtAuthenticationProvider provider)
	{
		return new JwtAuthenticationFilter(uds, provider);
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http, JwtAuthenticationFilter filter) throws Exception
	{
		// 위조 방지
		http.csrf(csrf->csrf.disable())
			.sessionManagement(session->
					session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		// 권한 (URL)
			.authorizeHttpRequests(auth->auth
					.requestMatchers("/","/login","/member/login").permitAll()
					.requestMatchers("/admin").hasRole("ADMIN")
					.requestMatchers("/user").hasAnyRole("USER","ADMIN","MANAGER")
					.anyRequest().permitAll()
					)
			.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
			
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder()
	{
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config)
	{
		return config.getAuthenticationManager();
	}

}
