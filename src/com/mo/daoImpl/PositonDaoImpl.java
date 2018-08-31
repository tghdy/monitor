package com.mo.daoImpl;

import com.mo.dao.IPositionDao;
import com.mo.po.Position;
import com.mo.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PositonDaoImpl implements IPositionDao {

    private QueryRunner runner = null;//查询运行器
    public PositonDaoImpl(){
        runner = new QueryRunner();
    }

    @Override
    public List<Map<String, Object>> getPositionInf() throws SQLException {
        String sql = "SELECT * FROM position";
        List<Map<String,Object>> list = runner.query(DBUtils.getConnection(),sql, new MapListHandler());
        return list;
    }

    @Override
    public void updatePositionInf(Position p, String id) throws SQLException {
        String sql = "update position set posname=?,longitude=?,latitude=? where posid=?";
        runner.update(DBUtils.getConnection(), sql, p.getPosname(),p.getLongitude(),p.getLatitude(),id);
    }

    @Override
    public void insertPositionInf(Position p) throws SQLException {
        String sql = "insert into position values(?,?,?,?)";
        runner.update(DBUtils.getConnection(), sql, p.getPosid(),p.getPosname(),p.getLongitude(),p.getLatitude());
    }

    @Override
    public void deletePositionInf(String id) throws SQLException {
        String sql = "delete from position where posid = ?";
        runner.update(DBUtils.getConnection(), sql ,id);
    }
}
