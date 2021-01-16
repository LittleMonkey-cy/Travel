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
* @author 用户注册
* @Description 
* @Date 2020/12/20
* @Param 
* @return 
**/
@WebServlet("/registUserServlet")
public class RegistUserServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            // 验证码校验
        String check = request.getParameter("check");
        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        // 为保证验证码只能保存一次
        session.removeAttribute("CHECKCODE_SERVER");
        // 进行比较判断
        if (checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            // 验证失败
            // 实例化对象
            ResultInfo info=new ResultInfo();
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码失败!");
            // 将数据打成json串返给前端ajax接收
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
           // 1.获取数据
            Map<String, String[]> map = request.getParameterMap();
        // 2.封装对象
            User user=new User();
            try {
                BeanUtils.populate(user,map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

       // 3.调用service完成注册
        // 实例化对象
        UserService userService=new UserServiceImpl() ;
        Boolean flag= userService.regist(user);

          // 调用用于封装后端返回前端数据对象

        ResultInfo info=new ResultInfo();
           // 4.响应结果
            if (flag){
                // 注册成功
                info.setFlag(true);
            }else {
                // 注册失败
                info.setFlag(false);
                info.setErrorMsg("注册失败!用户名被注册了");
            }
            // info对象序列化， 也可以用gson.jar包转化
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            // 将json数据写回客户端
            // 设置content-type
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);

        }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
