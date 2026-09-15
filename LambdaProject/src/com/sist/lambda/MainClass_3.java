package com.sist.lambda;
/*
 * 	filter	: 조건 검색
 * 	map		: 새로운 데이터 생성 => 소문자 : 대문자 변경
 * 	sorted	: 정렬
 * 	forEach	: 화면 출력
 * 
 */
import java.util.*;

public class MainClass_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = List.of("java","oracle","html","jsp","spring","abcdefg");
		list.stream()	// 데이터 읽기
			.sorted((a,b)->a.length()-b.length())
			.forEach(System.out::println);
		// compare(String,String)
		// 음수 > , 양수 < , == 동일
		
		System.out.println("==========================");
		
		List<Integer> nList = List.of(1,2,3,4,5,6,7,8);
		for(int n:nList)
		{
			if(n%2==0)
				System.out.println(n);
		}
		
		System.out.println("==========================");
		
		nList.stream()
			.filter(n->n%2==0)	// 조건문
			.forEach(System.out::println);
		
		System.out.println("==========================");
		// 문자열 길이 반환
		for(String w:list)
		{
			System.out.println(w.length());	// 실제 데이터가 아니라 변경된 데이터를 출력 => map
		}
		System.out.println("==========================");
		list.stream()
			.map(w->w.length())
			.forEach(System.out::println);
		
		System.out.println("==========================");
		
		List<Map<String, Object>> menuList = new ArrayList<Map<String, Object>>();
		Map<String,Object> maps = new HashMap<String,Object>();
		maps.put("홍길동", "ADMIN");
		maps.put("심청이", "USER");
		maps.put("강감찬", "USER");
		maps.put("이순신", "MANAGER");
		maps.put("박문수", "USER");
		menuList.add(maps);
		
		menuList.stream()
				.filter(m -> m.get("심청이").toString().contains("USER"))
				.forEach(m->System.out.println(m.get("심청이")));
	}
	/*
	 * 	람다식 사용시
	 * 	처리문장을 한 줄로 처리 : 게임 / 보안
	 * 	조건 / 변환
	 * 	복잡한 소스가 있는 경우
	 * 	디버깅이 중요한 코드
	 * 
	 * 	map / filter / sorted / forEach
	 * 
	 * 	권한 (보안) => 메뉴가 다르게 생성
	 * 
	 * 	List<Member> admins =
	 * 		users.stream()
	 * 			.filter(u->"ADMIN".equals(u.getRole())
	 * 			.toList()
	 * 	특정 필드 추출
	 * 	List<String> names
	 * 		users.stream()
	 * 			.map(Member::getName)
	 * 			.toList()
	 * 	중복 제거
	 * 	List<String> roles
	 * 		users.stream()
	 * 			.map(Member::getRole)
	 * 			.distinct()
	 * 			.toList()
	 * 
	 * 	보안 
	 * 		=> member
	 * 			userid , userpwd , username , enable
	 * 		=> 계정
	 * 			userid , role
	 * 					=> ROLE_ADMIN , ROLE_MANAGER ....
	 * 
	 * 		int sum=
	 * 			users.stream()
	 * 				.map(Member::getAge)
	 * 				.reduce(0,Integer::sum)
	 * 
	 * 		double avg=
	 * 			users.stream()
	 * 				.mapToInt(Member::getAge)
	 * 				.average()
	 * 				.orElse(0)	// 
	 * 		findFirst() 	// 첫번째 값 찾기
	 * 		groupingBy()	// 역할별
	 * 	
	 */

}
