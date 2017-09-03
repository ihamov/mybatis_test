package com.du.mybatis.test;

import java.io.IOException;
import java.io.InputStream;

import javax.print.attribute.standard.Finishings;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import com.du.mybatis.bean.Employee;
import com.du.mybatis.dao.EmployeeMapper;

public class MybatisTest {
    
    
    private SqlSession getSqlSession() throws IOException {
        String resource = "conf/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(inputStream);
        //获取SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
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

}
