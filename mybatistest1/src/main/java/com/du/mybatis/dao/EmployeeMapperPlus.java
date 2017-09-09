package com.du.mybatis.dao;

import com.du.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    
    /**
     * 根据id查询Employee对象，使用resultMap
     * @param id
     * @return
     */
    public Employee getEmpById(Integer id);
    
    
    
}
