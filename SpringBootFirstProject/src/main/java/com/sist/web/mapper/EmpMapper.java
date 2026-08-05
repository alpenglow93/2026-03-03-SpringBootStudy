package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/*
 * 	1. Mapper => SQL문장
 * 	2. DAO => Mapper 연동 : 데이터베이스 연결
 * 	3. Service => DAO의 추가 기능 = 로그인 / 주소 자르기
 * 	4. Controller => 브라우저에 출력 데이터 전송
 * 	5. JSP / HTML에서 출력
 * 			=> ThymeLeaf
 */
import java.util.*;
import com.sist.web.vo.*;
@Mapper
@Repository
public interface EmpMapper {
	@Select("SELECT empno,ename,job,TO_CHAR(hiredate,'yyyy-mm-dd') as dbday,sal "
			+ "FROM emp "
			+ "ORDER BY empno ASC ")
	public List<EmpVO> empListData();
}
