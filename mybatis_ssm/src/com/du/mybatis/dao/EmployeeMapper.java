package com.du.mybatis.dao;

import java.util.List;

import com.du.mybatis.bean.Employee;

public interface EmployeeMapper {
    
    /**
     * 单一参数查询
     * @param id
     * @return
     */
    public Employee getEmpById(Integer id);
    
    public List<Employee> getEmps();
    

}
