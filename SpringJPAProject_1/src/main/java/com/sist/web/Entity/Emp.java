package com.sist.web.Entity;

import java.util.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

/*
 * 	1. DQL => SELECT => 검색 : 메소드 규칙
 * 				findByName(String name) => eq
 * 				=> WHERE name=?
 * 	2. DML => INSERT / UPDATE / DELETE
 * 				|		|			| delete()
 * 				---------
 * 				| save()
 * 
 */

@Entity
@Table(name="EMP")
@Getter
@Setter
public class Emp {
	@Id	// primary key 설정
	private int empno;
	private String ename;
	private String job;
	private Integer mgr;	// column 안에 null 값이 있는 경우 wrapper 클래스 사용
	private Date hiredate;
	private int sal;		// null 이 없는 경우
	private Integer comm;
	
	// Deptno: 조인이 있는건 그냥 넣지 않는다 
	@ManyToOne
	@JoinColumn(name="deptno")
	private Dept dept;
}
