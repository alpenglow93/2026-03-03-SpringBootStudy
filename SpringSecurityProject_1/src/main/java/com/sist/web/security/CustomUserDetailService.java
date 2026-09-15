package com.sist.web.security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/*
 * 	ID: <input type=text name="username">
 * 	PW: <input type=password name="password">
 * 	=> 반드시 name 의 값을 username과 password로 줘야한다
 * 
 * 	로그인 화면
 * 		| POST => username=admin / password=1234
 * 		  / login =>
 * 	Spring Security
 * 	Authentication : 인증
 * 		| - Success : 성공 => / (home.html)
 * 					LoginSuccessHandler
 * 		| - Fail	: /login?error
 * 					LoginFailHandler
 * 					-------- 아이디나 비밀번호가 틀립니다
 * 		=> formLogin => 로그인 처리 후 인증 => 해당 접속자의 정보를 읽는다
 * 					=> id , password , enable , roles
 * 					=> Principal
 * 		   logout => session 해제
 * 
 * 		=> Controller
 * 			@GetMapping("/chat")
 * 			public String chat(HttpSession session)
 * 			{
 * 				UserVO vo = dao.infoData(p.username);
 * 				session.setAttribute("vo",vo);
 * 			}
 *  		==> Principal은 session 기반이지만 session이 아니기 때문에 session을 사용하려면 새로 생성해줘야함
 * 	
 * 
 */

@Service	// MyBatis 연동
public class CustomUserDetailService implements UserDetailsService
{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		if(username.equals("admin"))
		{
			return User.builder()
					.username("admin")
					.password("{noop}1234")	// {noop} : 암호화 없이 처리하겠다
					.roles("ADMIN")
					.build();
		}
		
		return User.builder()
				.username("user")
				.password("{noop}1234")
				.roles("USER")
				.build();
	}

}
