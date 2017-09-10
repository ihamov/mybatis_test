package com.du.mybatis.dao;

import com.du.mybatis.bean.Department;

public interface DepartmentMapper {
    /**
     * 根据id查询部门
     * @param id
     * @return
     */
    public Department getDeptById(Integer id);
}
