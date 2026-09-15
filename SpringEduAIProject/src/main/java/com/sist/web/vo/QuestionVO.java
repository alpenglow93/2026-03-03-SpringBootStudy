package com.sist.web.vo;

import lombok.Data;

/*
 * 	NO          NOT NULL NUMBER         
	TITLE       NOT NULL VARCHAR2(4000) 
	IMAGE                VARCHAR2(2000) 
	ANSWER      NOT NULL VARCHAR2(2000) 
	SCORE       NOT NULL NUMBER         
	DESCRIPTION          CLOB           
	THEME                NUMBER         
	TYPE                 NUMBER         
	DIFFICULTY  NOT NULL NUMBER(1) 
 */

@Data
public class QuestionVO {
	private int no,score,theme,type,difficulty;
	private String title,image,answer,description;
	private ExamOptionVO evo = new ExamOptionVO();
}
