package cn.love.travel.controller;

import cn.love.travel.pojo.ResultInfo;
import cn.love.travel.pojo.User;
import cn.love.travel.service.UserService;
import cn.love.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author 陈钰
 * @Description 登录
 * @Date 2020/12/20
 * @Param
 * @return
 **/
@WebServlet("/loginservlet")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 验证码
        String check = req.getParameter("check");
        // 从session中获取验证码
        HttpSession session = req.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        // 为保证验证码只能保存一次
        session.removeAttribute("CHECKCODE_SERVER");
        // 比较验证码是否正确
        if (checkcode_server == null || !checkcode_server.equalsIgnoreCase(check)) {
            // 验证失败
            ResultInfo info = new ResultInfo();
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码失败!");
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            resp.setContentType("application/json;charset=utf-8");
            resp.getWriter().write(json);
            return;
        }
        // 获取用户名和密码
        Map<String, String[]> map = req.getParameterMap();
        // 实例化对象
        User user = new User();
        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 实例化对象
        UserService service = new UserServiceImpl();
        // 调用service完成查询
        User u = service.login(user);

        ResultInfo info = new ResultInfo();

        // 判断用户名和密码是否正确
        if (u == null) {
            // 用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");
        }
        // 检查用户是否激活
        if (u != null && "N".equals(u.getStatus())) {
            // 未激活
            info.setFlag(false);
            info.setErrorMsg("您的账号还没被激活，请登录邮箱激活");
        }
        // 成功
        if (u != null && "Y".equals(u.getStatus())) {
            req.getSession().setAttribute("user", u);
            //登录成功
            info.setFlag(true);
        }

        // 响应数据给前端界面
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        mapper.writeValue(resp.getOutputStream(), info);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }
}
