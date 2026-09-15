package com.sist.web.jwt;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sist.web.service.CustomUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

/*
 * 	1. 사용자 정보를 저장 => UserDetailsService
 * 	2. 토큰 생성 => Provider
 * 	3. 통합 => Filter
 * 	4. 권한 => URL 접근 => Config
 * 	5. 실제 사용자로부터 요청 => Controller
 * 
 */

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter
{
	private final CustomUserDetailsService userDetailsService;
	private final JwtAuthenticationProvider provider;
	
	public JwtAuthenticationFilter(CustomUserDetailsService userDetailsService, JwtAuthenticationProvider provider)
	{
		this.userDetailsService = userDetailsService;
		this.provider = provider;
	}
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 토큰 생성
		String token = null;
		
		// 1. Header : Front에서 전송
		String header = request.getHeader("Authorization");
		// {Authorization: Bearer kdjsiddDfwiAZ31dkf1(토큰)} << json 이기 때문에 이런 형식으로 되어있음
		if(header != null && header.startsWith("Bearer "))
		{
			token = header.substring(7);
		}
		
		// 2. Cookie 확인
		if(token==null && request.getCookies()!=null)
		{
			for(Cookie cookie : request.getCookies())
			{
				if("accessToken".equals(cookie.getName()))
				{
					token = cookie.getValue();
					break;
				}
			}
		}
		
		// 3. JWT 검증
		if(token!=null && provider.validate(token))
		{
			String username = provider.getUsername(token);	// token에서 유저 이름을 가져온다
			UserDetails user = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken auth = 
					new UsernamePasswordAuthenticationToken(
							user,
							null,	// 인증 정보 => credentials : 자격 정보
							user.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		// 4. DB에서 사용자 조회
		// 5. Security 인증 객체 생성
		// 6. SecurityContext 저장
		
		// 7. 다음 Fileter / Controller 실행
		filterChain.doFilter(request, response);
	}

}
