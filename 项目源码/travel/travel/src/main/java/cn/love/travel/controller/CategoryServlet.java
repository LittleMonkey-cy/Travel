package cn.love.travel.controller;

import cn.love.travel.pojo.Category;
import cn.love.travel.pojo.ResultInfo;
import cn.love.travel.service.CategoryService;
import cn.love.travel.service.impl.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/category/*")
public class CategoryServlet extends BaseServlet {

    private CategoryService service = new CategoryServiceImpl();

    /**
     * 查询所有
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.调用service查询所有
        List<Category> cs = service.findAll();
        //2.序列化json返回
        // 方法一
       /* ObjectMapper mapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),cs);*/
        // 方法二调用封装的方法
        writeValue(cs, response);
    }

    /**
     * 删除单个分类
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        // service调用删除方法
        service.del(cid);
    }

    public void Add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取对象
        String cname = request.getParameter("cname");
        // 调用service查询
        Boolean flag = service.add(cname);
        ResultInfo info = new ResultInfo();
        if (flag) {
            // 添加成功
            info.setFlag(true);
        } else {
            info.setFlag(flag);
            info.setErrorMsg("添加失败！！！名称重复");
        }
        //响应数据
        writeValue(info, response);
    }

    /**
     * 查找单个分类
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cid = request.getParameter("cid");
        // 1.调用service更具id查询所有信息
        Category category = service.findOne(cid);
        writeValue(category, response);
    }

    /**
     * 更改类别信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        String cid = request.getParameter("cid");
        // 1.调用service查询所有
        service.update(cname, cid);

    }

    /**
     * 批量删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取对象
        String[] cids = request.getParameterValues("cid");
        service.delall(cids);
    }

    /**
     * 根据类别名称进行查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findName(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String cname = request.getParameter("cname");
        // 1.调用service根据类别名称查询所有
        Category category = service.findName(cname);
        writeValue(category, response);
    }

}
