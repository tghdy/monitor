package com.mo.daoImpl;

import com.mo.dao.IRfidDao;
import com.mo.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class RfidDaoImpl implements IRfidDao {

    private QueryRunner runner = null;//查询运行器
    public RfidDaoImpl(){
        runner = new QueryRunner();
    }

    public List<Map<String, Object>> getRfidInf() throws SQLException {
        String sql = "SELECT * FROM rfid";
        List<Map<String,Object>> list = runner.query(DBUtils.getConnection(),sql, new MapListHandler());
        return list;
    }
}
