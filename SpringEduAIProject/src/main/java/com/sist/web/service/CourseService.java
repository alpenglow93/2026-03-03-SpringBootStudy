package com.sist.web.service;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.oracle.OracleEduMapper;
import com.sist.web.mapper.postgres.PostgresEduMapper;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CourseService {
	private final OracleEduMapper oMapper;
	private final PostgresEduMapper pMapper;
	private final EmbeddingService eService;
	
	public void saveCourseVector(CourseVectorVO vo)
	{
		pMapper.saveCourseVector(vo);
	}
	
	/*
	 * 	StringBuffer	=> 비동기 
	 * 	StringBuilder	=> 동기
	 * 
	 * 	[0.1,0.2...]
	 */
	/*
	 * 	String content = createCourseContent(course);
	 * 			|
	 * 			5000자
	 * 	(겹치는 구간을 두어 문맥파악이 가능하도록 함 : 200자)
	 * 	----------------
	 * 	chunk 0 => 0~999
	 * 	----------------
	 * 	chunk 1 => 800~1799
	 * 	----------------
	 * 	chunk 2 => 1600~2599
	 * 	----------------
	 * 
	 * 	----------------
	 * 
	 * 	chunk ===> 문장을 자른다
	 * 	overlap ===> 자른 문장끼리 일부 겹치게 만든다 : 문맥처리
	 * 
	 */
	
	
	public void syncCourse()
	{
		List<CourseVO> courses = oMapper.courseAllData();
		for(CourseVO course:courses)
		{
			// AI 검색용 문서 => content
			String content = createCourseContent(course);
			List<String> chunks = chunkText(content, 1000, 200);
			
			int chunkNo = 0;
			for(String chunk:chunks)
			{				
				if(chunk == null || chunk.isBlank())
				{
					continue;
				}
				
				// Gemini
				float[] embedding = eService.createEmbedding(chunk);
				
				String eString = eService.toVectorString(embedding);
				//course.setContent(content);
				
				CourseVectorVO vo = new CourseVectorVO();
				vo.setTitle(course.getTitle());
				vo.setCourse_no(course.getNo());
				vo.setInstructor_no(course.getInstructor_no());
				vo.setContent(chunk);
				vo.setEmbedding(eString);
				vo.setChunk_no(chunkNo);
				saveCourseVector(vo);
				
				chunkNo++;
			}
		}
	}
	
	public String createCourseContent(CourseVO vo)
	{
		return """
				강의명: %s
				강의내용: %s
				강사번호: %s
				별점: %s
				수강생: %s
				판매가격: %s
				정가: %s
				""".formatted(
							vo.getTitle(),
							vo.getContent(),
							vo.getInstructor_no(),
							vo.getStar(),
							vo.getStudent_count(),
							vo.getPay_price(),
							vo.getRegular_price()
						);
	}
	
	// 분할
	public List<String> chunkText(String text, int chunkSize, int overlap)
	{
		// 결과값 저장
		List<String> chunks = new ArrayList<>();
		
		if(text==null || text.isBlank())
		{
			return chunks;
		}
		
		if(chunkSize <= 0)
		{
			throw new IllegalArgumentException("chunkSize는 0보다 커야 됩니다");
		}
		
		// overlap은 chunkSize보다 작은 경우
		if(overlap<0 || overlap>=chunkSize)
		{
			throw new IllegalArgumentException("overlap은 0 이상, chunkSize 미만인 경우만 가능합니다");
		}
		//----------------- 오류방지
		
		int start = 0;
		
		// 전체 문장을 끝까지 처리
		while(start < text.length())
		{
			// 현재 Chunk의 끝 위치
			int end = Math.min(start+chunkSize, text.length());
			String chunk = text.substring(start, end).trim();
			
			if(!chunk.isBlank())
			{
				chunks.add(chunk);
			}
			
			if(end >= text.length())
			{
				break;
			}
			
			start = end - overlap;
			
			/*
			 * 	end = 1000
			 * 	overlap = 200
			 * 	=> 다음 시작 => 800
			 */
		}
		
		return chunks;
	}
	
}
