package com.sist.lambda;

/*
 * 	함수형 인터페이스
 * 		=> 람다식은 함수형 인터페이스만 사용 가능
 * 		=> 추상메소드 1개만 존재
 * 		=> @FunctionalInterface 반드시 설정
 * 			=> 이 인터페이스는 람다용이다
 * 
 */
@FunctionalInterface
interface Calc 
{
	int sum(int a, int b);
}

public class MainClass_1 {

	/*
	 * 	문법은 간결 / 객체는 동일
	 * 	=> 람다가 빠른 이유
	 * 		일반 클래스			람다
	 * 		class 파일 생성	생성하지 않는다
	 * 		메모리가 크다		작다
	 * 		재사용 어렵다		쉽다
	 * 	=> 객체는 생성 => 클래스(.class)는 생성하지 않는다
	 * 	=> 가독성이 떨어진다 / 분석이 어려울 수 있다
	 * 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Calc c = new Calc() {
//			
//			@Override
//			public int sum(int a, int b) {
//				// TODO Auto-generated method stub
//				return a+b;
//			}
//		};
		
		Calc c = (a,b)->a+b;
		
		System.out.println(c.sum(10, 20));
		
	}

}
