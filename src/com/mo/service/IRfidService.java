package com.mo.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface IRfidService {

    //获取全部RFID信息
    public List<Map<String,Object>> getRfidInf()throws SQLException;

}
