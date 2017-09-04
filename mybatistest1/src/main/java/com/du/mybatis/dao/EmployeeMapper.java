package com.du.mybatis.dao;

import com.du.mybatis.bean.Employee;

public interface EmployeeMapper {
    
    public Employee getEmpById(Integer id);
    
    /**
     * 增删改可以设置返回值类型：Integer , Long, Boolean(大于1行即为true)
     * @param employee
     */
    public void addEmp(Employee employee);
    
    public void updateEmp(Employee employee);
    
    public void deleteEmpById(Integer id);

}
