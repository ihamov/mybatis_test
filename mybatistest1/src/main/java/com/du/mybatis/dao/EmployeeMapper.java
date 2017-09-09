package com.du.mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import com.du.mybatis.bean.Employee;

public interface EmployeeMapper {
    
    /**
     * 多参数查询--注解
     * @param id
     * @param lastName
     * @return
     */
    public Employee getEmpByIdAndLastName(@Param("id")Integer id, @Param("lastName")String lastName);
    /**
     * 多参数查询--封装到对象里
     * @param employee
     * @return
     */
    public Employee getEmpByEmp(Employee employee);
    /**
     * 多参数查询--封装在Map里
     * @param paraMap
     * @return
     */
    public Employee getEmpByMap(Map<String, Object> paraMap);
    
    /**
     * 单一参数查询
     * @param id
     * @return
     */
    public Employee getEmpById(Integer id);
    
    /**
     * 增删改可以设置返回值类型：Integer , Long, Boolean(大于1行即为true)
     * @param employee
     */
    public void addEmp(Employee employee);
    /**
     * 修改
     * @param employee
     */
    public void updateEmp(Employee employee);
    /**
     * 删除
     * @param id
     */
    public void deleteEmpById(Integer id);
    
    /**
     * 返回值为list
     * @param lastName
     * @return
     */
    public List<Employee> getEmpsByLastNameLike(String lastName);
    /**
     * 返回一条记录封装在map里：key为列名，value为值
     * @param id
     * @return
     */
    public Map<String, Object> getEmpByIdReturnMap(Integer id);
    /**
     * 返回多条记录封装成一个Map，key是主键，value为Employee对象
     * @param lastName
     * @return
     */
    @MapKey("id")//设置哪个字段作为key值
    public Map<Integer, Employee> getEmpsByLastNameReturnMap(String lastName);

}
