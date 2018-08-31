package com.mo.dao;

import com.mo.po.Position;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IPositionDao {

    //获取全部position信息
    public List<Map<String,Object>> getPositionInf()throws SQLException;

    //修改person信息
    public void updatePositionInf(Position p, String id)throws SQLException;

    //添加person信息
    public void insertPositionInf(Position p)throws SQLException;

    //删除person信息
    public void deletePositionInf(String id)throws SQLException;

}
