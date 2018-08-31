package com.mo.dao;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IRfidDao {

    //获取全部RFID信息
    public List<Map<String,Object>> getRfidInf()throws SQLException;

}
