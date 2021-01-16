package cn.love.travel.controller;

import cn.love.travel.service.AdminService;
import cn.love.travel.service.UserService;
import cn.love.travel.service.impl.AdminServiceImpl;
import cn.love.travel.service.impl.UserServiceImpl;
import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/user/*")
public class UserServlet extends BaseServlet {
    // 实例化对象
    private AdminService adminService = new AdminServiceImpl();
    private UserService userService = new UserServiceImpl();

    /**
     * 判断用户是否登录
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void personfind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session中获取登录用户
        User user1 = (User) request.getSession().getAttribute("user");

        int uid;
        if (user1 == null) {
            // 用户没有登录
            return;
        } else {
            // 用户已经登录
            uid = user1.getUid();
        }
        // 将user写回客户端
        User user = userService.personfind(uid);

        writeValue(user, response);

    }

    /**
     * 修改用户个人中心信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void Personupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取对象
        User user1 = (User) request.getSession().getAttribute("user");
        int uid;
        if (user1 == null) {
            // 用户没有登录
            return;
        } else {
            // 用户已经登录
            uid = user1.getUid();
        }

        // 封装
        Map<String, String[]> map = request.getParameterMap();
        // 实例化对象
        User user = new User();

        try {
            BeanUtils.populate(user, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service
        userService.Personupdate(user, uid);
    }

    /**
     * 分页展示
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        // 接收用户名称
        String unameStr = request.getParameter("uname");
        String uname = "";
        if (unameStr != null && unameStr.length() > 0 && !"null".equals(unameStr)) {
            uname = unameStr;
            uname = new String(uname.getBytes("iso-8859-1"), "utf-8");
        }

        // 当前页码，如果不传递，则默认为第一页
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        // 每页显示条数，如果不传递，默认每页显示8条记录
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 8;
        }

        PageBean<User> pb = userService.pageQuery(currentPage, pageSize, uname);

        // 4. 将pageBean对象序列化为json，返回给前台
        writeValue(pb, response);
    }

    /**
     * 批量删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取对象
        String[] uids = request.getParameterValues("uid");
        userService.delall(uids);
    }

    /**
     * 单个删除
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uid = request.getParameter("uid");
        userService.delUser(uid);
    }
}