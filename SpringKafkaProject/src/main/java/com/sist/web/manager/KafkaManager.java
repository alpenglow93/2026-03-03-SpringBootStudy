package com.sist.web.manager;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/*
 * 	Kafka (비동기적)
 * 		=> 서버와 서버로 대량의 이벤트/메세지 => 안정적으로 전달하는 분산 스트리밍
 * 	예)
 * 		사용자 A가 주문
 * 			|
 * 		  주문 서버
 * 			|
 * 		주문 완료 이벤트
 * 			|
 * 		  Kafka
 * 			|
 * 		-------------------------------
 * 		|				|				|
 * 		재고 서비스		결제 서비스			알림 서비스
 * 
 * 	=> Producer
 * 			|
 * 			|- 메세지 전송
 * 		Topic
 * 			|
 * 		Partition
 * 			|
 * 		Cusumer
 * 		--------
 * 			Producer ===> Kafka 메세지를 넣어주는 애플리케이션
 * 				|=> send
 * 			Topic => 메세지 분류해서 저장하는 공간
 * 				|
 * 		---------------------------
 * 		|			|			|
 * 		P1			P2			P2
 * 		-------------------------
 * 			|
 * 		Consumer => Kafka로부터 메세지를 읽어서 처리
 * 
 * 	
 * 	Producer
 * 		|
 * 	Topic
 * 		|
 * 	Consumer		
 *  
 */

@Component
public class KafkaManager {
	@KafkaListener(
		topics = "text-topic",
		groupId = "test-group"
	)
	public void receive(String message)
	{
		System.out.println("====================");
		System.out.println("Kafka 메세지 수신: " + message);
		System.out.println("====================");
	}
}
