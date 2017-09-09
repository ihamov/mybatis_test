package com.du.mybatis.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.du.mybatis.bean.Employee;
import com.du.mybatis.dao.EmployeeMapper;
import com.du.mybatis.dao.EmployeeMapperAnnotation;

public class MybatisTest {
    
    
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
    /**
     * 1、根据xml配置文件（全局配置文件）创建一个SqlSessionFactory对象 有数据源一些运行环境信息
     * 2、sql映射文件；配置了每一个sql，以及sql的封装规则等。 
     * 3、将sql映射文件注册在全局配置文件中
     * 4、写代码：
     *      1）、根据全局配置文件得到SqlSessionFactory；
     *      2）、使用sqlSession工厂，获取到sqlSession对象使用他来执行增删改查
     *          一个sqlSession就是代表和数据库的一次会话，用完关闭
     *      3）、使用sql的唯一标志来告诉MyBatis执行哪个sql。sql都是保存在sql映射文件中的。
     * @return
     * @throws IOException
     */
    @Test
    public void test() throws IOException{
        SqlSession sqlSession = getSqlSession();
        try {
            Employee employee = sqlSession.selectOne("com.du.mybatis.dao.EmployeeMapper.getEmpById", new Integer(1));
            System.out.println(employee.toString());
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
            
        }
        
    }

    /**
     * 1、接口式编程 原生： Dao ====> DaoImpl mybatis： Mapper ====> xxMapper.xml
     * 
     * 2、SqlSession代表和数据库的一次会话；用完必须关闭；
     * 3、SqlSession和connection一样它都是非线程安全。每次使用都应该去获取新的对象。
     * 4、mapper接口没有实现类，但是mybatis会为这个接口生成一个代理对象。 （将接口和xml进行绑定） EmployeeMapper
     * empMapper = sqlSession.getMapper(EmployeeMapper.class); 5、两个重要的配置文件：
     * mybatis的全局配置文件：包含数据库连接池信息，事务管理器信息等...系统运行环境信息 sql映射文件：保存了每一个sql语句的映射信息：
     * 将sql抽取出来。
     * 
     * @throws IOException
     */
    @Test
    public void test01() throws IOException{
        SqlSession sqlSession = getSqlSession();
        
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            Employee employee = employeeMapper.getEmpById(1);
            
            System.out.println(employee);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }
    
    /**
     * 测试注解
     * @throws IOException
     */
    @Test
    public void testAnnotation() throws IOException{
        SqlSession sqlSession = getSqlSession();
        
        
        try {
            EmployeeMapperAnnotation employeeMapperAnnotation = sqlSession.getMapper(EmployeeMapperAnnotation.class);
            
            Employee employee = employeeMapperAnnotation.getEmpById(1);
            
            System.out.println(employee);
            
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            sqlSession.close();
        }
    }
    
    /**
     * 测试增加
     * @throws IOException 
     */
    @Test
    public void testAdd() throws IOException{
        SqlSession sqlSession = getSqlSession(true);
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            Employee employee = new Employee();
            employee.setLastName("lixue");
            employee.setEmail("lixue@163.com");
            employee.setGender("1");
            
            employeeMapper.addEmp(employee);
            System.out.println(employee);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }
    
    /**
     * 测试修改
     * @throws IOException 
     */
    @Test
    public void testUpdate() throws IOException{
        SqlSession sqlSession = getSqlSession(true);
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            Employee employee = new Employee();
            employee.setId(2);
            employee.setLastName("jerry");
            employee.setEmail("jerry@126.com");
            employee.setGender("1");
            
            employeeMapper.updateEmp(employee);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }
    
    /**
     * 测试删除
     * @throws IOException 
     */
    @Test
    public void testDelete() throws IOException{
        SqlSession sqlSession = getSqlSession(true);
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            employeeMapper.deleteEmpById(2);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }
    @Test
    public void testMutilParamter() throws IOException{
        SqlSession sqlSession = getSqlSession();
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            Employee employee = employeeMapper.getEmpByIdAndLastName(1, "tom");
            System.out.println(employee);
            
            Employee employeeParam = new Employee();
            employeeParam.setId(2);
            employeeParam.setLastName("jerry");            
            Employee empByEmp = employeeMapper.getEmpByEmp(employeeParam);
            System.out.println(empByEmp);
            
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("id", 3);
            paraMap.put("lastName", "limei");
            Employee empByMap = employeeMapper.getEmpByMap(paraMap);
            System.out.println(empByMap);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
    }
    @Test
    public void testResultList() throws IOException{
        SqlSession sqlSession = getSqlSession();
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            List<Employee> empsByLastNameLike = employeeMapper.getEmpsByLastNameLike("%e%");
            
            for(Employee emp:empsByLastNameLike){
                System.out.println(emp);
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
        
    }
    
    @Test
    public void testResultMap() throws IOException{
        SqlSession sqlSession = getSqlSession();
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            Map<String, Object> empByIdReturnMap = employeeMapper.getEmpByIdReturnMap(1);
            
            for(Entry<String, Object> entry:empByIdReturnMap.entrySet()){
                System.out.println("key:"+entry.getKey());
                System.out.println("value:"+entry.getValue());
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
        
    }
    @Test
    public void testResultEmployeeMap() throws IOException{
        SqlSession sqlSession = getSqlSession();
        
        try {
            EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
            
            Map<Integer, Employee> empsByLastNameReturnMap = employeeMapper.getEmpsByLastNameReturnMap("%e%");
            
            for(Entry<Integer, Employee> entry:empsByLastNameReturnMap.entrySet()){
                System.out.println("key:"+entry.getKey().toString());
                System.out.println("value:"+entry.getValue().toString());
            }
            
            
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            sqlSession.close();
        }
        
    }
}
