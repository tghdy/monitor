package com.mo.serviceImpl;

import com.mo.dao.IPersonDao;
import com.mo.daoImpl.PersonDaoImpl;
import com.mo.po.Person;
import com.mo.service.IPersonService;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class PersonServiceImpl implements IPersonService {

    IPersonDao pd = new PersonDaoImpl();

    @Override
    public List<Map<String, Object>> getPersonInf() throws SQLException {
        return pd.getPersonInf();
    }

    @Override
    public void updatePersonInf(Person p, int id) throws SQLException {
        pd.updatePersonInf(p,id);
    }

    @Override
    public void insertPersonInf(Person p) throws SQLException {
        pd.insertPersonInf(p);
    }

    @Override
    public void deletePersonInf(int id) throws SQLException {
        pd.deletePersonInf(id);
    }
}
