package com.mo.serviceImpl;

import com.mo.dao.IPositionDao;
import com.mo.daoImpl.PositonDaoImpl;
import com.mo.po.Position;
import com.mo.service.IPositionService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PositonServiceImpl implements IPositionService {

    IPositionDao pd = new PositonDaoImpl();

    @Override
    public List<Map<String, Object>> getPositionInf() throws SQLException {
        return pd.getPositionInf();
    }

    @Override
    public void insertPositionInf(Position p) throws SQLException {
        pd.insertPositionInf(p);
    }

    @Override
    public void updatePositionInf(Position p, String id) throws SQLException {
        pd.updatePositionInf(p,id);
    }

    @Override
    public void deletePositionInf(String id) throws SQLException {
        pd.deletePositionInf(id);
    }
}
