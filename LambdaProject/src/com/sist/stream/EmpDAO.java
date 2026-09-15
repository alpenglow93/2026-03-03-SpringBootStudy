package com.sist.stream;

import java.util.*;
import java.sql.*;

public class EmpDAO {
	// SqlSessionFactory / JpaRepository
	private Connection conn;
	private PreparedStatement ps;
	private final String URL="jdbc:oracle:thin:@localhost:1521:XE";
	public EmpDAO()
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	public void getConnection()
	{
		try {
			conn = DriverManager.getConnection(URL,"hr","happy");
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	public void disConnection()
	{
		try {
			if(ps!=null) ps.close();
			if(conn!=null) conn.close();
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
	}
	
	// 전체 데이터 읽기
	public List<EmpVO> empAllData()
	{
		List<EmpVO> list = new ArrayList<EmpVO>();
		
		try {
			getConnection();
			String sql = "SELECT empno,ename,job,hiredate,sal "
					+ "FROM emp ";
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				EmpVO vo = new EmpVO();
				
				vo.setEmpno(rs.getInt(1));
				vo.setEname(rs.getString(2));
				vo.setJob(rs.getString(3));
				vo.setHiredate(rs.getDate(4));
				vo.setSal(rs.getInt(5));
				
				list.add(vo);
			}
			
			rs.close();
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally
		{
			disConnection();
		}
		
		return list;
	}

}
