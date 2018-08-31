package com.mo.serviceImpl;

import com.mo.dao.IRfidDao;
import com.mo.daoImpl.RfidDaoImpl;
import com.mo.service.IRfidService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RfidServiceImpl implements IRfidService {

    private static IRfidDao rd = new RfidDaoImpl();

    public List<Map<String, Object>> getRfidInf() throws SQLException {
        return rd.getRfidInf();
    }
}
