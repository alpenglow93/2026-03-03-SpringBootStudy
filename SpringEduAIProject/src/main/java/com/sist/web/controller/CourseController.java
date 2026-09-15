package com.sist.web.controller;

import java.util.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sist.web.service.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CourseController {
	private final CourseService cService;
	private final QuestionService qService;
	
	@GetMapping("/course")
	public String course_page()
	{
//		List<CourseVO> list = cService.courseAllData();
//		
//		System.out.println(list.size());
//		
//		for(CourseVO vo:list)
//		{
//			System.out.println(vo.getTitle());
//		}
//		
//		System.out.println(list);
//		
//		return "course";
		
		cService.syncCourse();
		return "데이터 저장 완료";
	}
	
	@GetMapping("/question")
	public String question_page()
	{
//		List<QuestionVO> list = cService.questionAllData();
//		System.out.println(list.size());
//		for(QuestionVO vo:list)
//		{
//			System.out.println(vo.getAnswer());
//		}
		qService.syncQuestion();
		return "question";
	}
	
}
