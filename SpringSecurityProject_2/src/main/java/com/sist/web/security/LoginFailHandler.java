package com.sist.web.security;

import java.io.IOException;

import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// Error
public class LoginFailHandler implements AuthenticationFailureHandler
{

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		// TODO Auto-generated method stub
		String errMsg="아이디나 비밀번호가 틀립니다";
		// instanceof 객체 비교 => 상속
		if(exception instanceof DisabledException)
		{
			// 휴면계정일 경우
			errMsg="휴면 계정입니다";
		}
		else if(exception instanceof LockedException)
		{
			// 잠긴계정일 경우
			errMsg="잠긴 계정입니다";
		}
		
		request.getSession().setAttribute("loginError", errMsg);
		response.sendRedirect("/login?error");
	}

}
