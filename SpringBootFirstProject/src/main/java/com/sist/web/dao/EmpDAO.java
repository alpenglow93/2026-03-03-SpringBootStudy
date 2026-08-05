package com.sist.web.dao;

import org.springframework.stereotype.Repository;
import java.util.*;
import com.sist.web.vo.*;

import lombok.RequiredArgsConstructor;

import com.sist.web.mapper.*;
@Repository
@RequiredArgsConstructor // 매개변수가 있는 생성자 + @Autowired
public class EmpDAO {
	private final EmpMapper mapper;
	
	public List<EmpVO> empListData()
	{
		return mapper.empListData();
	}
}
