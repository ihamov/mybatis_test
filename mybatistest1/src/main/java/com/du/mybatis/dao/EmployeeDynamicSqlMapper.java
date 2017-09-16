package com.du.mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.du.mybatis.bean.Employee;

public interface EmployeeDynamicSqlMapper {
    
    //根据条件进行查询 if标签
    public List<Employee> getEmpsByConditionIf(Employee employee);
    public List<Employee> getEmpsByConditionIf2(Employee employee);
    
    //根据条件进行查询 trim标签
    public List<Employee> getEmpsByConditionTrim(Employee employee);
    
    //根据条件进行查询 choose标签
    public List<Employee> getEmpsByConditionChoose(Employee employee);
    
    //根据条件判断进行更新
    public void updateEmp(Employee employee);
    
    //集合查询使用foreach遍历
    public List<Employee> getEmpsByConditionForeach(List<Integer> idList);
    
    //批量更新，使用foreach，同时可以使用@Param指定参数名
    public void addEmpsMysql(@Param("emps")List<Employee> emps);
    public void addEmpsMysql2(@Param("emps")List<Employee> emps);
    public void addEmpsOracle(@Param("emps")List<Employee> emps);
    public void addEmpsOracle2(@Param("emps")List<Employee> emps);
    
    //内置参数的使用
    public List<Employee> getEmpsTestInnerParameter(Employee employee);
    
}
