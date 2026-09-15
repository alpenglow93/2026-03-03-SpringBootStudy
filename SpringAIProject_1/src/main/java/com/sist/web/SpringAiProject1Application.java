package com.sist.web;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringAiProject1Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringAiProject1Application.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(ChatModel model)
	{
		System.out.println("초기화 자동 생성 => ChatModel: " + model);
		return args->{
			//String response = model.call("마포 여행지 알려줘");	// 입력하는 문장을 넘겨줌
			//System.out.println("[결과] " + response);
			
			System.out.println("-".repeat(100));
			String response = model.call("홍대 점심 메뉴");
			System.out.println("[결과] " + response);
		};
	}

}
