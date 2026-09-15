package com.sist.web.vo;

import lombok.Data;

/*
 * -- 	0. pgVector 확장 설치
		CREATE EXTENSION IF NOT EXISTS vector;
		
		-- 1. course_vector (강의 정보)
		DROP TABLE IF EXISTS course_vector CASCADE;
		CREATE TABLE course_vector (
		    id BIGSERIAL PRIMARY KEY,
		    course_no BIGINT NOT NULL,
		    title VARCHAR(1000) NOT NULL,
		    content TEXT,
		    instructor_no BIGINT,
		    embedding VECTOR(768),
		    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		    CONSTRAINT uk_course_vector_course UNIQUE (course_no)
		);
		CREATE INDEX idx_course_vector_embedding ON course_vector USING hnsw (embedding vector_cosine_ops);
		
		-- 2. question_vector (시험 문제 정보)
		DROP TABLE IF EXISTS question_vector CASCADE;
		CREATE TABLE question_vector (
		    id BIGSERIAL PRIMARY KEY,
		    question_no BIGINT NOT NULL,
		    course_no BIGINT,
		    title VARCHAR(4000) NOT NULL,
		    description TEXT,
		    theme INTEGER,
		    type INTEGER,
		    difficulty INTEGER NOT NULL,
		    answer VARCHAR(2000) NOT NULL,
		    option1 VARCHAR(2000),
		    option2 VARCHAR(2000),
		    option3 VARCHAR(2000),
		    option4 VARCHAR(2000),
		    content TEXT,
		    embedding VECTOR(768),
		    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		    CONSTRAINT uk_question_vector_question UNIQUE (question_no)
		);
		CREATE INDEX idx_question_vector_embedding ON question_vector USING hnsw (embedding vector_cosine_ops);
		
		-- 3. wrong_answer_vector (오답 이력 누적)
		DROP TABLE IF EXISTS wrong_answer_vector CASCADE;
		CREATE TABLE wrong_answer_vector (
		    id BIGSERIAL PRIMARY KEY,
		    enrollment_no BIGINT NOT NULL,
		    member_id BIGINT NOT NULL,
		    exam_no BIGINT NOT NULL,
		    question_no BIGINT NOT NULL,
		    exam_title VARCHAR(1000),
		    user_answer VARCHAR(2000),
		    correct_answer VARCHAR(2000),
		    question_title VARCHAR(4000),
		    description TEXT,
		    theme INTEGER,
		    type INTEGER,
		    difficulty INTEGER,
		    content TEXT,
		    embedding VECTOR(768),
		    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
		);
		CREATE INDEX idx_wrong_answer_vector_embedding ON wrong_answer_vector USING hnsw (embedding vector_cosine_ops);
		
		-- 4. learning_vector (학생별 학습 상태 최신화)
		DROP TABLE IF EXISTS learning_vector CASCADE;
		CREATE TABLE learning_vector (
		    id BIGSERIAL PRIMARY KEY,
		    member_id BIGINT NOT NULL,
		    exam_count INTEGER DEFAULT 0,
		    average_score NUMERIC(5,2) DEFAULT 0,
		    correct_count INTEGER DEFAULT 0,
		    wrong_count INTEGER DEFAULT 0,
		    accuracy_rate NUMERIC(5,2) DEFAULT 0,
		    weak_theme VARCHAR(2000),
		    learning_summary TEXT,
		    content TEXT,
		    embedding VECTOR(768),
		    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		    CONSTRAINT uk_learning_vector_member UNIQUE (member_id)
		);
		CREATE INDEX idx_learning_vector_embedding ON learning_vector USING hnsw (embedding vector_cosine_ops);
 */

@Data
public class CourseVectorVO {
	private int id,course_no,instructor_no,chunk_no;
	private String content,embedding,title;
	
}
