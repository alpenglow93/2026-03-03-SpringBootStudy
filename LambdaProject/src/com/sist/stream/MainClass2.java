package com.sist.stream;

import java.util.*;

public class MainClass2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		names.add("홍길동");
		names.add("박문수");
		names.add("심청이");
		names.add("이순신");
		names.add("김두한");
		for(String name:names)
		{
			System.out.println(name);
		}
		System.out.println();
		names.forEach(System.out::println);
		System.out.println();
		names.forEach(name->System.out.println(name));
		
		System.out.println("====================");
		List<String> colors = List.of("red", "green", "yellow", "magenta", "cyan", "blue", "black", "white");
		colors.stream()
			.filter(c->c.startsWith("b"))
			.map(String::toUpperCase)
			.forEach(System.out::println);
		
		System.out.println("====================");
		List<String> cList = colors.stream()
								.filter(c->c.startsWith("b"))
								.map(String::toUpperCase)
								.toList();
		cList.forEach(System.out::println);
		
		// 보안 관련 메소드 처리
	}

}
