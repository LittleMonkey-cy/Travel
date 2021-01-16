package cn.love.travel.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
* @author 陈钰
* @Description 用户退出（注销）
* @Date 2020/12/20
* @Param
* @return
**/
@WebServlet("/exitServlet")
public class ExitServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 通过session注销
        req.getSession().invalidate();
        resp.sendRedirect(req.getContextPath()+"/index.html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
