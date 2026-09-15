package com.sist.lambda;

// 람다+컬렉션
/*
 * 	for(String s:list)
 * 	{
 * 		System.out.println(s)
 * 	}
 * 
 * 	=> list.forEach(s->System.out.println(s))
 * 
 */

import java.util.*;
public class MainClass_2 {
	public static void main(String[] args) {
		List<String> colors = List.of("blue","green","red","yellow","black");
		
		for(String c:colors)
		{
			System.out.println(c);
		}
		
		System.out.println("====== 람다 이용 ======");
		
		colors.forEach(c->System.out.println(c));
		
		// 주요 연산
		/*
		 * 	중간 : filter , map , sorted , distinct
		 * 	최종 : forEach , collect , count
		 * 
		 * 	ADMIN / MANAGER / USER
		 * 
		 */
	}
}
