package cn.love.travel.controller;

import cn.love.travel.pojo.*;
import cn.love.travel.service.FavoriteService;
import cn.love.travel.service.impl.FavoriteServiceImpl;
import cn.love.travel.pojo.Favorite;
import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.Route;
import cn.love.travel.pojo.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author 收藏
 * @Description
 * @Date 2020/12/20
 * @Param
 * @return
 **/
@WebServlet("/favorite/*")
public class FavoriteServlet extends BaseServlet {
    // 实例化对象
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 添加收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void myFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        // 验证用户是否登录
        if (user == null) {
            // 用户没有登录
            return;
        } else {
            // 用户已经登录
            uid = user.getUid();
        }
        // 调用service方法
        List<Favorite> favorite = favoriteService.myFavorite(uid);
        // 将数据打成json串返回给前端ajax
        writeValue(favorite, response);
    }

    /**
     * 收藏的分页查询
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接受参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        User user = (User) request.getSession().getAttribute("user");

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

        // 3. 调用service查询PageBean对象
        int uid;
        // 判断用户是否登录
        if (user == null) {
            // 用户没有登录
            return;
        } else {
            // 用户已经登录
            uid = user.getUid();
        }
        PageBean<Favorite> pb = favoriteService.pageQuery(uid, currentPage, pageSize);

        // 4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);

    }

    /**
     * 删除收藏信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        // 调用service
        favoriteService.delFavorite(rid);
    }

    /**
     * 收藏排行榜
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void topFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> rs = favoriteService.topFavorite();
        writeValue(rs, response);
    }

    /**
     * 最新景点
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void newFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> rs = favoriteService.newFavorite();
        writeValue(rs, response);
    }

    /**
     * 主题景点
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void themeFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> rs = favoriteService.themeFavorite();
        writeValue(rs, response);
    }

}


