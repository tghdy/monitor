package com.mo.dao;

import com.mo.po.Person;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IPersonDao {

    //获取全部person信息
    public List<Map<String,Object>> getPersonInf()throws SQLException;

    //修改person信息
    public void updatePersonInf(Person p,int id)throws SQLException;

    //添加person信息
    public void insertPersonInf(Person p)throws SQLException;

    //删除person信息
    public void deletePersonInf(int id)throws SQLException;

}
