package com.seoulit.emp;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmpHandler {

    @Resource
    EmpDao empDao;

    @GetMapping("/api/emp")
    public List<HashMap<String, Object>> findEmpList() {

        return empDao.selectEmpList();

    }

}