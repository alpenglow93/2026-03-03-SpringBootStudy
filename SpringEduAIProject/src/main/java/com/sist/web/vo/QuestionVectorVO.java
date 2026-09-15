package com.sist.web.vo;

import lombok.Data;

@Data
public class QuestionVectorVO {
	private Long id,question_no;
	private int theme,type,difficulty;
	private String title,description,answer,option1,option2,option3,option4,content,embedding;
}
