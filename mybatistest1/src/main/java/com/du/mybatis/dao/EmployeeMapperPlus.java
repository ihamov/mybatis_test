package com.du.mybatis.dao;

import com.du.mybatis.bean.Employee;

public interface EmployeeMapperPlus {
    
    /**
     * 根据id查询Employee对象，使用resultMap
     * @param id
     * @return
     */
    public Employee getEmpById(Integer id);
    /**
     * 根据id查询Employee对象，同时要查询出Deptment对象信息 方式一
     * @param id
     * @return
     */
    public Employee getEmpAndDeptById(Integer id);
    /**
     * 根据id查询Employee对象，同时要查询出Deptment对象信息 方式二
     * @param id
     * @return
     */
    public Employee getEmpAndDeptById2(Integer id);
    
}
