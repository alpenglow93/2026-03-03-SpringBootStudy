package com.sist.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sist.web.Entity.Emp;

/*
 * 	JPA
 * 	1. 메소드 규칙 : 자동으로 SQL문장을 제작 => JPQL
 * 		장점 : SQL문장이 자동 생성 => SQL을 모르는 경우에서 사용이 가능
 * 				메소드명으로 SQL을 확인 가능 => 가독성이 좋다
 * 		단점 : 이름의 한계 => 메소드명이 길어질 수 있다
 * 				findBySalGreaterThanAndEnameLikeAndJobLikeOrderByHiredateDesc()
 * 				WHERE sal>? AND ename LIKE ? AND job LIKE ?
 * 				ORDER BY hiredate DESC
 * 				동적쿼리 사용이 불가 => MyBatis
 * 					사용빈도 : 8:2
 * 		사용처 : 단순 조회 / 기본 값 findByEmpno(int empno)
 * 				조회목적
 * 				findByColumn명연산자
 * 					finsBySalIsNotNull
 * 				WHERE sal isNotNull
 * 				---------------------
 * 				< > <= >=
 * 				< LessThan (LessThanEqual)
 * 				> GreaterThan (GreaterThanEqual)
 * 	2. JPQL : JPA에서 제공하는 객체 중심의 SQL문장
 * 			장점 : 표준기술 => 라이브러리가 필요 없다 => 바로 사용 가능
 * 					객체지향 : 테이블이 아니고 엔티티 객체를 이용
 *			단점 : 문자열 기반 => 오타가 있는 경우 에러 처리가 어렵다
 *					동적쿼리 사용이 어렵다
 *			사용처 : 복잡하지 않은 SQL / 수정 / 삭제시에 주로 사용
 * 	3. QueryDSL
 * 			타입의 안정성
 * 			단점 : 초기 설정이 어렵다 => Q-class를 생성
 * 				QueryDSL의 문법이 까다롭다
 * 			=> 복잡한 조인 / 필터링 / 페이징
 * 	----------------------------------------------
 * 	단순 조회 : 메소드 규칙 => SELECT * 
 * 			=> Getter만 있는 interface
 * 	복잡한 동적 쿼리 / 검색 => QueryDSL / MyBatis
 * 	수정 . 삭제 . 정적 쿼리 : JPQL
 * 
 */

@Repository
public interface EmpJpqlRepository extends JpaRepository<Emp, Integer>
{
	//			모든 데이터		객체이름 객체별칭
	@Query("SELECT e FROM Emp e")	// Emp는 테이블이 아니고 Entity 객체명
	// 반드시 별칭을 사용해야 된다
	public List<Emp> empListData();
	
	// public Emp findByEmpno(int empno);
	@Query("SELECT e FROM Emp e WHERE e.empno=:empno")
	public Emp empDetailData(@Param("empno") int empno);
	
	// List<Emp> findByEname(String ename);
	@Query("SELECT e FROM Emp e WHERE e.ename=:ename")
	public List<Emp> empEnameFind(@Param("ename") String ename);
	
	//List<Emp> findByEnameStartsWith(String ename);
	@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT(:ename,'%')") // CONCAT() : 문자열 결합
	// :ename||'%'
	public List<Emp> empEnameStartsLike(@Param("ename") String ename);
	
	@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%',:ename)")
	// :ename||'%'
	public List<Emp> empEnameEndsLike(@Param("ename") String ename);
	
	// List<Emp> findByEnameContains(String ename);
	@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT('%', :ename, '%')")
	public List<Emp> empLikeData(@Param("ename") String ename);
	
	// List<Emp> findBySalGreaterThanEqual(int sal);
	@Query("SELECT e FROM Emp e WHERE e.sal>=:sal")
	public List<Emp> findBySalGreaterThanEqual(@Param("sal") int sal);
	
	@Query("SELECT e FROM Emp e WHERE e.sal<=:sal")
	public List<Emp> findBySalLessThanEqual(@Param("sal") int sal);
	
	// List<Emp> findBySalBetween(int min, int max);
	@Query("SELECT e FROM Emp e WHERE e.sal BETWEEN :min AND :max")
	public List<Emp> findBySalBetween(@Param("min") int min, @Param("max") int max);
	
	// List<Emp> findByJobAndSalGreaterThan(String job, int sal);
	@Query("SELECT e FROM Emp e "
			+ "WHERE e.job=:job AND e.sal>:sal")
	public List<Emp> findByJobAndSalGreaterThan(@Param("job") String job, @Param("sal") int sal);
	
	// 조인문
	// List<Emp> findByDeptDname(String dname);
	@Query("SELECT e FROM Emp e "
			+ "JOIN e.dept d "	// Emp Entity 안에 있는 이름 dept 
			+ "WHERE d.dname=:dname")
	List<Emp> findByDeptDname(@Param("dname") String dname);
	
	// List<Emp> findByDeptDnameContains(String dname);
	@Query("SELECT e FROM Emp e "
			+ "JOIN e.dept d "	// Emp Entity 안에 있는 이름 dept 
			+ "WHERE d.dname LIKE CONCAT('%', :dname, '%')")
	List<Emp> findByDeptDnameContains(@Param("dname") String dname);
	
//	// List<Emp> findTop3ByOrderBySalDesc();
//	@Query("SELECT e FROM Emp "
//			+ "ORDER BY e.sal DESC")
//	List<Emp> findTop3ByOrderBySalDesc();
	
	// List<Emp> findDistinctByJob(String job);
//	@Query("SELECT e DISTINCT FROM Emp e "
//			+ "WHERE e.job=:job")
//	List<Emp> findDistinctByJob(@Param("job") String job);
//	@Query("SELECT DISTINCT e.job FROM Emp e")
//	Emp findDistinctByJob();
	
	// List<Emp> findByCommIsNull();
	@Query("SELECT e FROM Emp e "
			+ "WHERE e.comm IS NULL")
	List<Emp> findByCommIsNull();
	
	// List<Emp> findByJobNot(String job);
	@Query("SELECT e FROM Emp e "
			+ "WHERE e.job<>:job")
	List<Emp> findByJobNot(@Param("job") String job);
	
	// List<Emp> findByDeptDeptnoIn(List<Integer> deptnos);
	@Query("SELECT e FROM Emp e "
			+ "WHERE e.dept.deptno IN :deptnos")
	List<Emp> findByDeptDeptnoIn(@Param("deptnos") List<Integer> deptnos);
	
	
	/*
	 * // findBy => WHERE
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
	// SELECT e FROM Emp e ORDER BY sal DESC
	
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
	 */
}
