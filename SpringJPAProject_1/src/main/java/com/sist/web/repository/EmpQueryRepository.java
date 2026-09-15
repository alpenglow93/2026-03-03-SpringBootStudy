package com.sist.web.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sist.web.Entity.Emp;
import com.sist.web.Entity.QDept;
import com.sist.web.Entity.QEmp;

import lombok.RequiredArgsConstructor;

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
	
 */
// @Query("SELECT") : JPQL
// Emp findByEmpno(int empno) : 메소드 규칙
// Querydsl

/*
 * 	1. window<showview<ther<gradle<Greadle Tasks
 * 	2. ./gradlew clean compileJava
 * 		터미널에서 실행
 * 	3. project폴더에서 gradle > refresh
 * 	4. project < clean 설정
 * 	Q-class : 데이터베이스를 검색할때 사용하는 Java 코드 생성
 * 		=> Query DSL => 자동으로 생성
 * 		Dept => QDept
 */

@Repository
@RequiredArgsConstructor
public class EmpQueryRepository {
	private final JPAQueryFactory queryFactory;
	
	public Emp findByEmpno(int empno)
	{
		QEmp emp = QEmp.emp;
		
		return (Emp)queryFactory.from(emp)
						   .where(emp.empno.eq(empno))
						   .fetchOne();
	}
	
	// List<Emp> findByEname(String ename);
	//@Query("SELECT e FROM Emp e WHERE e.ename=:ename")
	/*
	 * 	from(테이블 : Q-class 객체)
	 * 	where(조건)
	 * 	orderBy(컬럼.desc())
	 * 	groupBy(컬럼)
	 * 	having(조건)
	 * 
	 * 	연산자 : eq =
	 * 			gt >
	 * 			goe <
	 */
	public List<Emp> findByEname(String ename) {
		QEmp emp = QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
						.where(emp.ename.eq(ename))
						.fetch();
	}
	
	//List<Emp> findByEnameStartsWith(String ename);
	//@Query("SELECT e FROM Emp e WHERE e.ename LIKE CONCAT(:ename,'%')")
	public List<Emp> findByEnameStartsWith(String ename)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				.where(emp.ename.startsWith(ename))
				.fetch();
	}
	
	public List<Emp> findByEnameEndsWith(String ename)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				.where(emp.ename.endsWith(ename))
				.fetch();
	}
	
	public List<Emp> findByEnameContains(String ename)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>)queryFactory.from(emp)
				.where(emp.ename.contains(ename))
				.fetch();
	}
	
	// 비교연산자
	/*
	 * 	= : eq()	emp.sal.eq(3000) sal=3000
	 * ------------------------------------------
	 * 	< : lt()	emp.sal.lt(3000) sal<3000
	 * 				LessThan
	 * 	> : gt()	emp.sal.gt(3000) sal>3000
	 * 				GreaterThan
	 * 	<= : loe()	emp.sal.loe(3000) sal<=3000
	 * 				LessThanEqusl
	 * 	>= : goe()	emp.sal.goe(3000) sal>=3000
	 * 				GreaterThanEqual
	 * ------------------------------------------
	 * 	!= : ne()	emp.sal.ne(3000) sal<>3000
	 * 
	 * 	메소드가 연산자 명칭을 그대로 사용
	 * 	between() , in() 
	 * 
	 */
	
	//@Query("SELECT e FROM Emp e WHERE e.sal>=:sal")
	//public List<Emp> findBySalGreaterThanEqual(@Param("sal") int sal);
	public List<Emp> findBySalGraterThanEqual(int sal)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				.where(emp.sal.goe(sal))
				.fetch();
		// sal>=? sal<=? sal<? sal>? sal<>? sal=?
		// emp.sal.goe(sal)
		// emp.sal.loe(sal)
		// emp.sal.lt(sal)
		// emp.sal.gt(sal)
		// emp.sal.ne(sal)
		// emp.sal.eq(sal)
	}
	
	//@Query("SELECT e FROM Emp e WHERE e.sal BETWEEN :min AND :max")
	//public List<Emp> findBySalBetween(@Param("min") int min, @Param("max") int max);
	public List<Emp> findBySalBetween(int min, int max)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				.where(emp.sal.between(min, max))
				.fetch();
	}
	
	// AND / OR
//	@Query("SELECT e FROM Emp e "
//			+ "WHERE e.job=:job AND e.sal>:sal")
//	public List<Emp> findByJobAndSalGreaterThan(@Param("job") String job, @Param("sal") int sal);
	public List<Emp> findByJobAndSalGreaterThan(String job, int sal)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				//.where(emp.job.eq(job),emp.sal.gt(sal))	// and는 , 로 사용
				.where(emp.job.eq(job).and(emp.sal.gt(sal))) // 아니면 이렇게도 사용 (or도 마찬가지)
				.fetch();
	}
	
	// 부서명 / 근무지
	// List<Emp> findByDeptDname(String dname);
//	@Query("SELECT e FROM Emp e "
//			+ "JOIN e.dept d "	// Emp Entity 안에 있는 이름 dept 
//			+ "WHERE d.dname=:dname")
//	List<Emp> findByDeptDname(@Param("dname") String dname);
	public List<Emp> findByDeptDname(String dname)
	{
		QEmp emp = QEmp.emp;
		QDept dept = QDept.dept;
		return (List<Emp>) queryFactory.from(emp)
				.join(emp.dept, dept)
				.where(dept.dname.eq(dname))
				.fetch();
	}
	
	public List<Emp> findByDeptDnameLike(String dname)
	{
		QEmp emp = QEmp.emp;
		QDept dept = QDept.dept;
		return (List<Emp>) queryFactory.from(emp)
				.join(emp.dept, dept)
				.where(dept.dname.contains(dname))
				.fetch();
	}
	
	// 정렬
	public List<Emp> findByOrderBySal()
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				.orderBy(emp.sal.desc())
				.fetch();
	}
	
	// Top-N
	public List<Emp> findByTop3Sal()
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				.orderBy(emp.sal.desc())
				.limit(3)
				.fetch();
	}
	
	// Distinct
	public List<Integer> findDistinctSal()
	{
		QEmp emp = QEmp.emp;
		return (List<Integer>) queryFactory.select(emp.sal)
				.distinct()
				.from(emp)
				.fetch();
	}
	
	// NULL
//	@Query("SELECT e FROM Emp e "
//			+ "WHERE e.comm IS NULL")
//	List<Emp> findByCommIsNull();
	List<Emp> findByCommIsNull()
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				.where(emp.comm.isNull())
				.fetch();
		// isNotNull()
	}	
	
	// NOT
	// List<Emp> findByJobNot(String job);
//	@Query("SELECT e FROM Emp e "
//			+ "WHERE e.job<>:job")
//	List<Emp> findByJobNot(@Param("job") String job);
	List<Emp> findByJobNot(String job)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				.where(emp.job.ne(job))
				.fetch();
	}
	
	// IN
	// List<Emp> findByDeptDeptnoIn(List<Integer> deptnos);
//	@Query("SELECT e FROM Emp e "
//			+ "WHERE e.dept.deptno IN :deptnos")
//	List<Emp> findByDeptDeptnoIn(@Param("deptnos") List<Integer> deptnos);
	public List<Emp> findByDeptDeptnoIn(List<Integer> deptnos)
	{
		QEmp emp = QEmp.emp;
		return (List<Emp>) queryFactory.from(emp)
				.where(emp.dept.deptno.in(deptnos))
				.fetch();
	}
	
	
	
	
}





















