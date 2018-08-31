package com.mo.servlet;

import com.mo.service.IRfidService;
import com.mo.serviceImpl.RfidServiceImpl;
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

@WebServlet("/rfid")
public class RfidServlet extends HttpServlet {

    private IRfidService rs = new RfidServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        if ("rfidDetail".equals(request.getParameter("method"))) {
            rfidDetail(request, response);
        }
    }

    private void rfidDetail(HttpServletRequest request, HttpServletResponse response) {
        JsonData json =null;
        try {
            List<Map<String, Object>> list = rs.getRfidInf();
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
