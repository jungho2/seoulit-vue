package com.seoulit.emp;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EmpDao {

    public List<HashMap<String, Object>> selectEmpList();

}