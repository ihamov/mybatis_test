package com.du.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.du.mybatis.bean.Department;
import com.du.mybatis.bean.Employee;
import com.du.mybatis.dao.EmployeeDynamicSqlMapper;

public class MybatisTest2 {
    
    private SqlSession getSqlSession() throws IOException {
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        return sqlSession;
    }
    private SqlSession getSqlSession(boolean isAutoCommit) throws IOException {
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession(isAutoCommit);
        return sqlSession;
    }
    
    @Test
    public void testDynamicSql() throws IOException{
        SqlSession sqlSession = getSqlSession();
        try {
            Employee employee = new Employee();
            employee.setId(3);
            employee.setLastName("%e%");
            
            EmployeeDynamicSqlMapper employeeDynamicSqlMapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            List<Employee> emps = employeeDynamicSqlMapper.getEmpsByConditionIf(employee);
            List<Employee> emps1 = employeeDynamicSqlMapper.getEmpsByConditionTrim(employee);
            List<Employee> emps2 = employeeDynamicSqlMapper.getEmpsByConditionChoose(employee);
            
            System.out.println(emps);
            System.out.println(emps1);
            System.out.println(emps2);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
            
        }
    }
    
    @Test
    public void testDynamicSql2() throws IOException{
        SqlSession sqlSession = getSqlSession();
        try {
            Employee employee = new Employee();
            employee.setId(3);
            employee.setLastName("admin");
            
            EmployeeDynamicSqlMapper employeeDynamicSqlMapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            employeeDynamicSqlMapper.updateEmp(employee);
            sqlSession.commit();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
            
        }
    }
    
    @Test
    public void testDynamicSql3() throws IOException{
        SqlSession sqlSession = getSqlSession();
        try {
            
            
            EmployeeDynamicSqlMapper employeeDynamicSqlMapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            List<Employee> emps = employeeDynamicSqlMapper.getEmpsByConditionForeach(Arrays.asList(1,3,4));
            System.out.println(emps);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
            
        }
    }
    
    @Test
    public void testDynamicSql4() throws IOException{
        SqlSession sqlSession = getSqlSession();
        try {
            
            List<Employee> emps = new ArrayList<Employee>();
            
            emps.add(new Employee(null, "zhangsan12", "1", "zhangsan1@126.com", new Department(1,null,null)));
            emps.add(new Employee(null, "lisi12", "1", "lisi1@126.com", new Department(2,null,null)));
            emps.add(new Employee(null, "wangwu12", "1", "wangwu1@126.com", new Department(1,null,null)));
            
            
            EmployeeDynamicSqlMapper employeeDynamicSqlMapper = sqlSession.getMapper(EmployeeDynamicSqlMapper.class);
            employeeDynamicSqlMapper.addEmpsMysql2(emps);
            sqlSession.commit();
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
            
        }
    }
}
