package com.sist.web.mapper.oracle;


import java.util.*;

import org.apache.ibatis.annotations.Mapper;

import com.sist.web.vo.*;

@Mapper
public interface OracleEduMapper {
	/*
	 *	 <select id="courseAllData" resultType="com.sist.web.vo.CourseVO">
			SELECT * FROM course
			ORDER BY no ASC;
		</select>
	*/
	public List<CourseVO> courseAllData();
	
	/*
		<resultMap type="com.sist.web.vo.QuestionVO" id="questionMap">
			<result property="evo.option1" column="option1"/>
			<!-- 
				ExamOption evo = new ExamOption()
				evo.setOption1()
			 -->
			<result property="evo.option2" column="option2"/>
			<result property="evo.option3" column="option3"/>
			<result property="evo.option4" column="option4"/>
		</resultMap>
		<select id="questionAllData" resultType="com.sist.web.vo.QuestionVO">
			SELECT q.no,q.title,q.answer,q.score,q.description,q.theme,q.type,q.difficulty,
					o.option1,o.option2,o.option3,o.option4
			FROM exam_question q
			LEFT JOIN exam_option o
			ON q.no=o.question_no
			ORDER BY q.no ASC
		</select>
	 */
	public List<QuestionVO> questionAllData();
	
	
}
