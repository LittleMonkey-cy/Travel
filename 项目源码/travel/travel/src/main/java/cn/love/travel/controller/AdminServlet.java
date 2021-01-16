package cn.love.travel.controller;

import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.ResultInfo;
import cn.love.travel.pojo.Seller;
import cn.love.travel.service.AdminService;
import cn.love.travel.service.impl.AdminServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

@WebServlet("/admin/*")
public class AdminServlet extends BaseServlet {
    private AdminService adminService=new AdminServiceImpl();
    // 管理员登录
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 验证码
        String check = request.getParameter("check");
        // 从session中获取验证码
        HttpSession session = request.getSession();
        String checkcode_server = (String) session.getAttribute("CHECKCODE_SERVER");
        // 为保证验证码只能保存一次
        session.removeAttribute("CHECKCODE_SERVER");
        // 比较判断
        if (checkcode_server==null||!checkcode_server.equalsIgnoreCase(check)){
            // 验证失败
            ResultInfo info=new ResultInfo();
            // 注册失败
            info.setFlag(false);
            info.setErrorMsg("验证码失败!");
            ObjectMapper mapper=new ObjectMapper();
            String json = mapper.writeValueAsString(info);
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(json);
            return;
        }
        // 获取用户名和密码
        Map<String, String[]> map = request.getParameterMap();
        // 封装user对象
        Seller seller = new Seller();
        try {
            BeanUtils.populate(seller,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 调用service完成查询
        AdminService service=new AdminServiceImpl();
        Seller s= service.adminlogin(seller);

        ResultInfo info=new ResultInfo();

        // 判断用户名和密码是否正常
        if (s==null){
            // 用户名密码错误
            info.setFlag(false);
            info.setErrorMsg("用户名或密码错误");

        }else {
            request.getSession().setAttribute("seller",s);
            // 登录成功
            info.setFlag(true);
        }
        // 响应数据
        writeValue(info,response);

    }

    /**
     * 查询数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session中获取登录用户
        Seller seller = (Seller) request.getSession().getAttribute("seller");
        // 将user写回客户端
        if (seller==null){
            //用户没有登录
            return;
        }else {
            // 用户已经登录
            writeValue(seller,response);
        }


    }

    /**
     * 退出
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void exitAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session中获取登录用户
        request.getSession().invalidate();
        response.sendRedirect(request.getContextPath()+"/admin.html");
        // 将user写回客户端
    }

    public void ListAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 调用service查询数据
        List<Seller> sellers= adminService.listadmin();
        // 响应数据
        writeValue(sellers,response);
    }

    /**
     * 添加管理员信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void AddAdmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取对象
        Map<String,String[]>map= request.getParameterMap();

        // 封装数据
        Seller seller=new Seller();
        try {
            BeanUtils.populate(seller,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 调用service查询
         Boolean flag= adminService.addadmin(seller);
        ResultInfo info=new ResultInfo();

        if (flag){
            // 添加成功
            info.setFlag(true);
        }else {
            info.setFlag(flag);
            info.setErrorMsg("添加失败！！！用户名重复");
        }
        // 响应数据
        writeValue(info,response);
    }

    /**
     * 单个删除管理员信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deladmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sid = request.getParameter("sid");
        // service调用删除方法
        adminService.deladmin(sid);
    }

    /**
     * 查询管理员信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOneadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取id
        String sid = request.getParameter("sid");
        Seller seller= adminService.findOneadmin(sid);
        // service调用
        writeValue(seller,response);
    }

    /**
     * 修改管理员信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取对象
        Map<String,String[]>map= request.getParameterMap();
        // 实例化对象
        Seller seller=new Seller();

        try {
            BeanUtils.populate(seller,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //调用service
        adminService.update(seller);
        response.sendRedirect(request.getContextPath()+"/admin.html");
    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void  delall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取对象
        String[] sids = request.getParameterValues("sid");
        adminService.delall(sids);
    }

    /**
     * 分页展示数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void  pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String nameStr = request.getParameter("name");
        String name ="";
        if (nameStr!=null && nameStr.length()>0 &&!"null".equals(nameStr)) {
            name=nameStr;
            name = new String(name.getBytes("iso-8859-1"), "utf-8");
        }

        // 设置当前页码，如果不传递，则默认为第一页
        int currentPage = 0;
        if(currentPageStr != null && currentPageStr.length() > 0){
            currentPage = Integer.parseInt(currentPageStr);
        }else{
            currentPage = 1;
        }
        // 每页显示条数，如果不传递，默认每页显示8条记录
        int pageSize = 0;
        if(pageSizeStr != null && pageSizeStr.length() > 0){
            pageSize = Integer.parseInt(pageSizeStr);
        }else{
            pageSize = 8;
        }

        PageBean<Seller> pb = adminService.pageQuery(currentPage, pageSize,name);

        // 4. 将pageBean对象序列化为json，返回给前端数据
        writeValue(pb,response);
    }

    /**
     * 管理员个人信息的更改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void Personupdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取对象
        Seller seller1 = (Seller) request.getSession().getAttribute("seller");

        int sid;
        // 判断用户是否登录
        if (seller1==null){
            // 用户没有登录
            return;
        }else {
            // 用户已经登录
           sid= seller1.getSid();
        }
        Map<String,String[]>map= request.getParameterMap();
        // 封装
        Seller seller=new Seller();

        try {
            BeanUtils.populate(seller,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        // 调用service更新数据
        adminService.Personupdate(seller,sid);
    }

    /**
     * 显示个人中心数据
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void personfind(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从session中获取登录用户
        Seller seller1 = (Seller) request.getSession().getAttribute("seller");
        int sid;
        // 判断用户是否登录
        if (seller1==null){
            // 用户没有登录
            return;
        }else {
            // 用户已经登录
            sid= seller1.getSid();
        }
        // 将user写回客户端
        Seller seller= adminService.personfind(sid);
        writeValue(seller,response);
    }
}