package com.du.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.du.mybatis.bean.Employee;

public interface EmployeeMapper {
    
    /**
     * 多参数查询
     * @param id
     * @param lastName
     * @return
     */
    public Employee getEmpByIdAndLastName(@Param("id")Integer id, @Param("lastName")String lastName);
    
    public Employee getEmpByEmp(Employee employee);
    
    public Employee getEmpByMap(Map<String, Object> paraMap);
    
    
    public Employee getEmpById(Integer id);
    
    /**
     * 增删改可以设置返回值类型：Integer , Long, Boolean(大于1行即为true)
     * @param employee
     */
    public void addEmp(Employee employee);
    
    public void updateEmp(Employee employee);
    
    public void deleteEmpById(Integer id);
    
    /**
     * 返回list
     * @param lastName
     * @return
     */
    public List<Employee> getEmpsByLastNameLike(String lastName);

}
