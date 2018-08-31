package com.mo.daoImpl;

import com.mo.dao.IPersonDao;
import com.mo.po.Person;
import com.mo.util.DBUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PersonDaoImpl implements IPersonDao {

    private QueryRunner runner = null;//查询运行器
    public PersonDaoImpl(){
        runner = new QueryRunner();
    }

    @Override
    public List<Map<String, Object>> getPersonInf() throws SQLException {
        String sql = "SELECT * FROM person";
        List<Map<String,Object>> list = runner.query(DBUtils.getConnection(),sql, new MapListHandler());
        return list;
    }

    @Override
    public void updatePersonInf(Person p,int id) throws SQLException {
        String sql = "update person set name=?,sex=?,birthtime=?,rfid=? where id=?";
        runner.update(DBUtils.getConnection(), sql, p.getName(),p.getSex(),p.getBirthtime(),p.getRfid(),id);
    }

    @Override
    public void insertPersonInf(Person p) throws SQLException {
        String sql = "insert into person(name,sex,birthtime,rfid)values(?,?,?,?)";
        runner.update(DBUtils.getConnection(), sql, p.getName(), p.getSex(),p.getBirthtime(),p.getRfid());
    }

    @Override
    public void deletePersonInf(int id) throws SQLException {
        String sql = "delete from person where id = ?";
        runner.update(DBUtils.getConnection(), sql ,id);
    }
}
