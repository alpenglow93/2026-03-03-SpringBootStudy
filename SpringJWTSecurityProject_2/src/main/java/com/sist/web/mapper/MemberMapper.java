package com.sist.web.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.sist.web.vo.MemberVO;

/*
 * 	자바
 * 		Wrapper 클래스
 * 		제네릭
 * 		컬렉션
 * 		예외처리 종류
 * 	자바스크립트
 * 		클로저 / 호이스팅
 * 	오라클
 * 		JOIN / SubQuery
 * 	---------------------
 * 	Redis / React
 * 
 */

@Mapper
@Repository
public interface MemberMapper {
	@Select("SELECT userid,username,userpwd,enable,sex "
			+ "FROM springmember "
			+ "WHERE userid=#{userid}")
	public MemberVO findByUserid(String userid);
}
