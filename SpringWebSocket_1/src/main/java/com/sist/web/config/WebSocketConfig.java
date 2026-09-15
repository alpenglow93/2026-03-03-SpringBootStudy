package com.sist.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/*
 * 	new SockJS("/ws-chat") : 서버 연결
 * 		|
 * 	SpringBoot WebSocket
 * 		|
 * 	WebSocket 연결
 * 		|
 * 	setAllowedOriginPatterns("*") : 접속 허용
 * 		|
 * 	withSocketJS()
 * 	=> SockJS를 이용해서 통식이 가능하게 지원
 * 	
 */

@Configuration
@EnableWebSocketMessageBroker	// STOMP 기반의 WebSocket 기능을 활성화
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer 
{
	// 클라이언트가 WebSocket 서버에 처음 접속할 주소 등록
	// /ws-chat
	/*
	 * 	registry.addEndPoint("/ws-chat")
	 * 	=> new SockJs("/ws-chat")
	 * 	아래(클라이언트)에서 해당 주소로 접속한다
	 * 
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
		// 1. 클라이언트가 서버에 접속할 URI 주소
		// origin => 모든(*) 클라이언트가 접속이 가능하게
		// => 실제는 지정된 도메인만 허용
		registry.addEndpoint("/ws-chat")
				.setAllowedOriginPatterns("*")
				.withSockJS();
		
	}

	// URI 이용		자바 채팅 => 번호 이용
	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// TODO Auto-generated method stub
		//WebSocketMessageBrokerConfigurer.super.configureMessageBroker(registry);
		// 채널 => 클라이언트가 서버에서 보낸 데이터를 읽어서 출력
		registry.enableSimpleBroker("/topic");
		// 메세지를 보내는 경우(보내는 곳)
		registry.setApplicationDestinationPrefixes("/app");
	}
	
}
