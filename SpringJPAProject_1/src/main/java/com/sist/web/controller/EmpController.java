package com.sist.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;
import com.sist.web.Entity.*;
import com.sist.web.repository.*;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class EmpController {
	private final EmpMethodRepository eDao;
	private final EmpJpqlRepository eDao2;
	private final EntityManager em;
	private final EmpQueryRepository eDao3;
	
	@GetMapping("/emp")
	public void emp_method()
	{
		//Emp emp=eDao.findByEmpno(7788);
		//List<Emp> list = eDao.findByEnameStartsWith("A");
		//List<Emp> list = eDao.findByEnameEndsWith("N");
		//List<Emp> list = eDao.findByEnameContains("K");
		//List<Emp> list = eDao.findByOrderBySalDesc();
		//List<Emp> list = eDao.findByJobAndSalGreaterThan("SALESMAN", 1000);
		//List<Emp> list = eDao.findByDeptDnameContains("인");
		
		// ==================================================================================
		
		
		//List<Emp> list = eDao2.empListData();
		//List<Emp> list = eDao2.empEnameFind("SCOTT");
		//List<Emp> list = eDao2.empEnameStartsLike("S");
		//List<Emp> list = eDao2.empEnameEndsLike("N");
		//List<Emp> list = eDao2.empLikeData("A");
		//List<Emp> list = eDao2.findBySalBetween(2000, 3000);
		//List<Emp> list = eDao2.findByJobAndSalGreaterThan("SALESMAN", 1000);
		//List<Emp> list = eDao2.findByDeptDname("개발팀");
		//List<Emp> list = eDao2.findByDeptDnameContains("개");
		
//		// Top-N
//		String jpql = "SELECT e FROM Emp e "
//				+ "ORDER BY e.sal DESC";
//		List<Emp> list = em.createQuery(jpql,Emp.class).setMaxResults(5).getResultList();
		
//		//Emp emp = eDao2.findDistinctByJob();
//		String jpql = "SELECT DISTINCT e.job FROM Emp e";
//		List<String> list = em.createQuery(jpql,String.class).getResultList();
		
		//List<Emp> list = eDao2.findByJobNot("SALESMAN");
//		List<Emp> list = eDao2.findByDeptDeptnoIn(List.of(10,20));
		
		//===============================================================================================
		
		List<Emp> list = null;
		//Emp e = eDao3.findByEmpno(7788);
		//list = eDao3.findByEname("SCOTT");
		//list = eDao3.findByEnameStartsWith("S");
		//list = eDao3.findByEnameEndsWith("T");
		//list = eDao3.findByEnameContains("A");
		//list = eDao3.findBySalBetween(2000, 3000);
		//list = eDao3.findByOrderBySal();
		
		//List<Integer> list = eDao3.findDistinctSal();
		
		//list = eDao3.findByTop3Sal();
		list = eDao3.findByDeptDnameLike("팀");
		
		
		System.out.println("===============================================================");
		for(Emp emp:list)
		{
			System.out.println(emp.getEmpno()+" "
					+emp.getEname()+" "
					+emp.getJob()+" "
					+emp.getHiredate()+" "
					+emp.getSal()+" "
					+emp.getDept().getDeptno()
					);
			
		}
		
//		System.out.println("=====================");
//		for(String job:list)
//		{
////			System.out.println(emp.getEmpno()+" "
////					+emp.getEname()+" "
////					+emp.getJob()+" "
////					+emp.getHiredate()+" "
////					+emp.getSal());
//			System.out.println(job);
//			
//		}
		
//		System.out.println(e.getEmpno()+" "
//				+e.getEname()+" "
//				+e.getJob()+" "
//				+e.getHiredate()+" "
//				+e.getSal());
		//System.out.println(list);
		
	}
}
