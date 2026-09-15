package com.sist.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sist.web.Entity.Emp;
import java.util.*;
import com.sist.web.Entity.QEmp;

public interface EmpMethodRepository extends JpaRepository<Emp, Integer>	// 제네릭에 클래스 이름과 ID의 데이터형을 집어넣는다
{
	// findBy => WHERE
	// 1. 상세보기 => empno 검색
	// WHERE empno=?
	public Emp findByEmpno(int empno);
	
	// 2. ename 검색
	List<Emp> findByEname(String ename);
	// WHERE ename=? ==> equals
	
	// 3. LIKE => A%(startswith) %A%(contains) %A(endswith)
	List<Emp> findByEnameStartsWith(String ename);	// 이것만 인덱스 적용됨 (많이 쓰임)
	// WHERE ename LIKE '?%'
	List<Emp> findByEnameEndsWith(String ename);
	// WHERE ename LIKE '%?'
	List<Emp> findByEnameContains(String ename);
	// WHERE ename LIKE '%?%'
	
	// 4. 비교
	// 이상(GreaterThan) / 이하(LessThan)
	// WHERE sal>3000	sal>=3000	sal<2000	sal<=2000
	List<Emp> findBySalGreaterThanEqual(int sal);
	// WHERE sal>=?
	List<Emp> findBySalLessThanEqual(int sal);
	// WHERE sal<=?
	
	//Between => ? ?
	List<Emp> findBySalBetween(int min, int max);
	// WHERE sal BETWEEN ? AND ?
	
	// AND => JOB / SAL
	List<Emp> findByJobAndSalGreaterThan(String job, int sal);
	// WHERE job=? AND sal>?
	
	// OR
	List<Emp> findByJobOrEname(String job, String ename);
	// WHERE job=? OR Ename=?
	
	// 컬럼명으로 emp 검색
	List<Emp> findByDeptDname(String dname);
	List<Emp> findByDeptLoc(String loc);
	
	// 부서명 Like
	List<Emp> findByDeptDnameContains(String dname);
	
	// 정렬
	List<Emp> findByOrderBySalDesc();
	// ORDER BY sal DESC
	
	// Top-N : 정렬을 한 후 가장 많은 N개의 값 가져오기
	List<Emp> findTop3ByOrderBySalDesc();
	// WHERE rownum<=3 ORDER BY sal DESC
	
	// 중복 제거
	List<Emp> findDistinctByJob(String job);
	
	// NOT NULL / NULL
	List<Emp> findByCommIsNull();
	// WHERE comm ISNULL
	List<Emp> findByCommIsNotNull();
	// WHERE comm ISNOTNULL
	
	// IN
	List<Emp> findByDeptDeptnoIn(List<Integer> deptnos);
	// List<Integer> list = List.of(10,20,30)
	
	// NOT
	List<Emp> findByJobNot(String job);
	// WHERE NOT job=?
	
	// 메소드 규칙
}
