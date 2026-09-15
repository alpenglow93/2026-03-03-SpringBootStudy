package com.sist.web.mapper.postgres;

import com.sist.web.vo.*;

public interface PostgresEduMapper {
	/*
 * 		<insert id="saveCourseVector" parameterType="com.sist.web.vo.CourseVectorVO">
			INSERT INTO course_vector(
				course_no,
				title,
				content,
				instructor_no,
				embedding
			)
			VALUES(
				#{course_no},
				#{title},
				#{content},
				#{instructor_no},
				CAST(
					#{embedding} AS vector
				)
			)
		</insert>
	 * 
	 */
	public void saveCourseVector(CourseVectorVO vo);
	
	// <insert id="saveQuestionVector" parameterType="com.sist.web.vo.QuestionVectorVO">
	public void saveQuestionVector(QuestionVectorVO vo);
}
