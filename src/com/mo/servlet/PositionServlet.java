package com.mo.servlet;

import com.mo.po.Position;
import com.mo.service.IPositionService;
import com.mo.serviceImpl.PositonServiceImpl;
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

@WebServlet("/position")
public class PositionServlet extends HttpServlet {

    private IPositionService ps = new PositonServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if ("positionDetail".equals(request.getParameter("method"))) {
            positionDetail(request, response);
        }
        if ("addpos".equals(request.getParameter("method"))) {
            addpos(request, response);
        }
        if ("updatepos".equals(request.getParameter("method"))) {
            updatepos(request, response);
        }
        if ("deletepos".equals(request.getParameter("method"))) {
            deletepos(request, response);
        }
    }

    private void deletepos(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        String posid = request.getParameter("posid");
        try {
            ps.deletePositionInf(posid);
            json = new JsonData(JsonData.SUCCESS,"成功");
        } catch (SQLException e) {
            e.printStackTrace();
            json = new JsonData(JsonData.FAILED,"出错");
        }finally {
            output(response,json);
        }
    }

    private void updatepos(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        Position p = new Position(request.getParameter("posid"),
                request.getParameter("posname"),
                request.getParameter("longitude"),
                request.getParameter("latitude"));
        try {
            ps.updatePositionInf(p,p.getPosid());
            json = new JsonData(JsonData.SUCCESS,"成功");
        } catch (SQLException e) {
            e.printStackTrace();
            json = new JsonData(JsonData.FAILED,"出错");
        }finally {
            output(response,json);
        }
    }

    private void addpos(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        Position p = new Position(request.getParameter("posid"),
                request.getParameter("posname"),
                request.getParameter("longitude"),
                request.getParameter("latitude"));
        try {
            ps.insertPositionInf(p);
            json = new JsonData(JsonData.SUCCESS,"成功");
        } catch (SQLException e) {
            e.printStackTrace();
            json = new JsonData(JsonData.FAILED,"出错");
        }finally {
            output(response,json);
        }
    }

    private void positionDetail(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        try {
            List<Map<String, Object>> list = ps.getPositionInf();
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
