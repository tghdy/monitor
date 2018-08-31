package com.mo.servlet;

import com.mo.po.Person;
import com.mo.service.IPersonService;
import com.mo.serviceImpl.PersonServiceImpl;
import com.mo.util.JsonData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet("/person")
public class PersonServlet extends HttpServlet {

    private IPersonService ps = new PersonServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if ("personDetail".equals(request.getParameter("method"))) {
            personDetail(request, response);
        }
        if ("addper".equals(request.getParameter("method"))) {
            addper(request, response);
        }
        if ("updateper".equals(request.getParameter("method"))) {
            updateper(request, response);
        }
        if ("deleteper".equals(request.getParameter("method"))) {
            deleteper(request, response);
        }
    }

    private void deleteper(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println(id);
        try {
            ps.deletePersonInf(id);
            json = new JsonData(JsonData.SUCCESS,"成功");
        } catch (SQLException e) {
            e.printStackTrace();
            json = new JsonData(JsonData.FAILED,"出错");
        }finally {
            output(response,json);
        }
    }

    private void updateper(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        Person p = new Person(request.getParameter("name"),
                Integer.parseInt(request.getParameter("sex")),
                request.getParameter("birthtime"),
                request.getParameter("rfid"));
        p.setId(Integer.parseInt(request.getParameter("id")));
        try {
            ps.updatePersonInf(p,p.getId());
            json = new JsonData(JsonData.SUCCESS,"成功");
        } catch (SQLException e) {
            e.printStackTrace();
            json = new JsonData(JsonData.FAILED,"出错");
        }finally {
            output(response,json);
        }
    }

    private void addper(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        Person p = new Person(request.getParameter("name"),
                Integer.parseInt(request.getParameter("sex")),
                request.getParameter("birthtime"),
                request.getParameter("rfid"));
        try {
            ps.insertPersonInf(p);
            json = new JsonData(JsonData.SUCCESS,"成功");
        } catch (SQLException e) {
            e.printStackTrace();
            json = new JsonData(JsonData.FAILED,"出错");
        }finally {
            output(response,json);
        }
    }

    private void personDetail(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        try {
            List<Map<String, Object>> list = ps.getPersonInf();
            if(list == null){
                json = new JsonData(JsonData.FAILED,"不存在此信息");
            }else {
                json = new JsonData(JsonData.SUCCESS,"成功",list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            json = new JsonData(JsonData.FAILED,"出错");
        }finally {
            output(response,json);
        }
    }

    private void output(HttpServletResponse response, JsonData jsonData) {
        try {

            response.setCharacterEncoding("utf-8");
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println(jsonData.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
