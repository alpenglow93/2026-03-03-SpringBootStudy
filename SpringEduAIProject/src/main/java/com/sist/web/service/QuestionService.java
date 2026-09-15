package com.sist.web.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sist.web.mapper.oracle.OracleEduMapper;
import com.sist.web.mapper.postgres.PostgresEduMapper;
import com.sist.web.vo.QuestionVO;
import com.sist.web.vo.QuestionVectorVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {
	private final OracleEduMapper oMapper;
	private final PostgresEduMapper pMapper;
	private final EmbeddingService eService;
	
	public void saveQuestionVector(QuestionVectorVO vo)
	{
		pMapper.saveQuestionVector(vo);
	}
	
	public void syncQuestion()
	{
		List<QuestionVO> questions = oMapper.questionAllData();
		for(QuestionVO vo:questions)
		{
			String content = createQuestionDescription(vo);
			float[] embedding = eService.createEmbedding(content);
			String eString = eService.toVectorString(embedding);
			
			QuestionVectorVO qvo = new QuestionVectorVO();
			qvo.setQuestion_no((long)vo.getNo());
			qvo.setTitle(vo.getTitle());
			qvo.setDescription(vo.getDescription());
			qvo.setTheme(vo.getTheme());
			qvo.setType(vo.getType());
			qvo.setDifficulty(vo.getDifficulty());
			qvo.setAnswer(vo.getAnswer());
			qvo.setOption1(vo.getEvo().getOption1());
			qvo.setOption2(vo.getEvo().getOption2());
			qvo.setOption3(vo.getEvo().getOption3());
			qvo.setOption4(vo.getEvo().getOption4());
			
			qvo.setContent(content);
			qvo.setEmbedding(eString);
			saveQuestionVector(qvo);
		}
	}
	
	public String createQuestionDescription(QuestionVO vo)
	{
		return """
				문제:
	            %s
	            선택지 1:
	            %s
	            선택지 2:
	            %s
	            선택지 3:
	            %s
	            선택지 4:
	            %s
	            정답:
	            %s
	            해설:
	            %s
	            주제:
	            %s
	            문제 유형:
	            %s
	            난이도:
	            %s
	            """.formatted(
	                vo.getTitle(),
	                vo.getEvo().getOption1(),
	                vo.getEvo().getOption2(),
	                vo.getEvo().getOption3(),
	                vo.getEvo().getOption4(),
	                vo.getAnswer(),
	                vo.getDescription(),
	                vo.getTheme(),
	                vo.getType(),
	                vo.getDifficulty()
	            );
	}
}
