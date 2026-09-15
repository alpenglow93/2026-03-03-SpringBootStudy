package com.sist.lambda;

/*
 * 	${ sessionScope.id != null }	=> JSP
 * 	${ session.id != null }			=> ThymeLeaf
// 람다식
 * 	1. 익명의 함수 (함수명이 없다)
 * 	2. 메소드를 값처럼 전달
 * 	3. 코드 간결화
 * 	4. 함수형 프로그램 스타일
 * 	5. 기본 문법
 * 		(매개변수) -> {실행문}		()=>{} (자바스크립트)
 * 	6. 타입 생략이 가능
 * 		(int a) -> (a)
 * 		매개변수 	=> (a)->a
 * 		한 줄 수행	{return a+1} ==> a+1
 * 		예) (a,b) -> a+b
 * 
 * 		member ->
 * 	7. 인터페이스 / 클래스 => 메소드가 1개인 경우에만 적용 
 * 
 */

public class MainClass {
	public static void main(String[] args) {
		// 쓰레드 작업
//		Runnable r = new Runnable() {
//			
//			@Override
//			public void run() {
//				// TODO Auto-generated method stub
//				System.out.println("Thread 실행");
//			}
//		};
		
		Runnable r = ()->System.out.println("Thread 실행");
		// () run 메소드
		new Thread(r).start();
	}
}
