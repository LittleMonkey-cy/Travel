package cn.love.travel.controller;

import cn.love.travel.pojo.*;
import cn.love.travel.service.FavoriteService;
import cn.love.travel.service.RouteService;
import cn.love.travel.service.impl.FavoriteServiceImpl;
import cn.love.travel.service.impl.RouteServiceImpl;
import cn.love.travel.util.UploadUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/route/*")
public class RouteServlet extends BaseServlet {
    // 实例化对象
    private RouteService routeService = new RouteServiceImpl();
    private FavoriteService favoriteService = new FavoriteServiceImpl();

    /**
     * 分页查询
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
        String cidStr = request.getParameter("cid");

        // 接受rname 线路名称
        String rnameStr = request.getParameter("rname");
        String rname = "";
        if (rnameStr != null && rnameStr.length() > 0 && !"null".equals(rnameStr)) {
            rname = rnameStr;
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }
        // 类别id
        int cid = 0;
        // 2.处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        // 当前页码，如果不传递，则默认为第一页
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        // 每页显示条数，如果不传递，默认每页显示5条记录
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 5;
        }

        // 3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        // 4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);

    }

    /**
     * 根据id查询一个景点的详情页面
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void findOne(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 接收id
        String rid = request.getParameter("rid");
        // 调用service
        Route route = routeService.findOne(rid);
        // 转json写回客户端
        writeValue(route, response);
    }

    /**
     * 判断是否收藏
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1获取线路rid
        String rid = request.getParameter("rid");
        // 2.获取当前用户user
        User user = (User) request.getSession().getAttribute("user");
        // 用户ID
        int uid;
        // 判断用户是否登录
        if (user == null) {
            // 用户没有登录，直接返回return
            return;
        } else {
            // 用户已经登录，获取用户id
            uid = user.getUid();
        }
        // 调用FavoriteService业务逻辑层查询是否有收藏
        boolean flag = favoriteService.isFavorite(rid, uid);
        // 转为json串写回客户端
        writeValue(flag, response);
    }

    /**
     * 收藏的添加
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addFavorite(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        // 获取用户信息
        User user = (User) request.getSession().getAttribute("user");
        int uid;
        // 判断用户是否登录
        if (user == null) {
            // 用户没有登录
            return;
        } else {
            // 用户已经登录
            uid = user.getUid();
        }
        // 调用service方法
        favoriteService.addFavorite(rid, uid);
    }

    /**
     * 国内景点
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void inland(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> rs = routeService.inlandOne();
        writeValue(rs, response);
    }

    /**
     * 国外景点
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void foreign(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Route> rs = routeService.foreigndOne();
        writeValue(rs, response);
    }

    /**
     * 轮播图
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void slideshow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<RouteBigimg> rb = routeService.slideshow();
        writeValue(rb, response);
    }

    /**
     * 管理员景点展示
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void pageQueryadmin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 1.接收参数
        String currentPageStr = request.getParameter("currentPage");
        String pageSizeStr = request.getParameter("pageSize");
        String cidStr = request.getParameter("cid");

        // 接收线路名称
        String rnameStr = request.getParameter("rname");
        String rname = "";
        if (rnameStr != null && rnameStr.length() > 0 && !"null".equals(rnameStr)) {
            rname = rnameStr;
            rname = new String(rname.getBytes("iso-8859-1"), "utf-8");
        }
        // 类别id
        int cid = 0;
        // 2.处理参数
        if (cidStr != null && cidStr.length() > 0 && !"null".equals(cidStr)) {
            cid = Integer.parseInt(cidStr);
        }
        // 当前页码，如果不传递，则默认为第一页
        int currentPage = 0;
        if (currentPageStr != null && currentPageStr.length() > 0) {
            currentPage = Integer.parseInt(currentPageStr);
        } else {
            currentPage = 1;
        }
        // 每页显示条数，如果不传递，默认每页显示5条记录
        int pageSize = 0;
        if (pageSizeStr != null && pageSizeStr.length() > 0) {
            pageSize = Integer.parseInt(pageSizeStr);
        } else {
            pageSize = 8;
        }

        // 3. 调用service查询PageBean对象
        PageBean<Route> pb = routeService.pageQuery(cid, currentPage, pageSize, rname);

        // 4. 将pageBean对象序列化为json，返回
        writeValue(pb, response);

    }

    /**
     * 通过路线id单个删除路线信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void del(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String rid = request.getParameter("rid");
        routeService.delroute(rid);

    }

    /**
     * 批量删除景点信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void delall(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String[] rids = request.getParameterValues("rid");

        routeService.delrouteall(rids);

    }

    /**
     * 添加景点信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 使用fileuload保存图片和景点信息。
            // 1.1map集合，存放信息
            Map<String, Object> map = new HashMap<>();
            // 1.2创建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 1.3核心上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析request
            List<FileItem> list = upload.parseRequest(request);


            for (FileItem fi : list) {
                // 获取name属性
                String key = fi.getFieldName();
                // 判断是否为普遍组件
                if (fi.isFormField()) {
                    // 普通的组件
                    map.put(key, fi.getString("utf-8"));
                } else {
                    // 文件
                    // 获取文件名称
                    String name = fi.getName();
                    // 获取真实名称
                    String realName = UploadUtils.getRealName(name);
                    // 调用UUID工具类给文件附上一个随机名称
                    String uuidName = UploadUtils.getUUIDName(realName);
                    // 随机目录
                    String dir = UploadUtils.getDir();
                    // 利用输入流获得文件内容
                    InputStream is = fi.getInputStream();
                    // 创建输出流
                    // 获取small正式路径
                    String productPath = getServletContext().getRealPath("/img/test/img");
                    File file = new File(productPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }

                    FileOutputStream os = new FileOutputStream(new File(file, uuidName));

                    // 拷贝
                    IOUtils.copy(is, os);
                    // 释放资源
                    os.close();
                    is.close();
                    // 删除文件
                    fi.delete();

                    // 景点路径
                    map.put(key, "img/test/img" + "/" + uuidName);
                    // 1.实例化route对象
                    Route route = new Route();
                    // 1.1手动加入时间
                    map.put("rdate", new Date());
                    BeanUtils.populate(route, map);


                    // 2.调用service增加景点信息
                    routeService.add(route);

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            throw new RuntimeException("保存商品失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/scenic.html");

    }

    /**
     * 景点信息进行更改
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 使用fileuload保存图片和景点信息。
            //  1.1map集合，存放信息
            Map<String, Object> map = new HashMap<>();

            // 1.2创建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 1.3核心上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析request
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem fi : list) {
                // 获取name属性
                String key = fi.getFieldName();
                // 判断是否为普遍组件
                if (fi.isFormField()) {
                    // 普通的组件
                    map.put(key, fi.getString("utf-8"));
                } else {
                    // 文件
                    // 获取文件名称
                    String name = fi.getName();
                    // 获取真实名称
                    String realName = UploadUtils.getRealName(name);
                    // 调用UUID工具类给文件附上一个随机名称
                    String uuidName = UploadUtils.getUUIDName(realName);
                    // 随机目录
                    String dir = UploadUtils.getDir();
                    // 文件内容
                    InputStream is = fi.getInputStream();
                    // 创建输出流
                    // 获取small正式路径
                    String productPath = getServletContext().getRealPath("/img/test/img");
                    File file = new File(productPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    FileOutputStream os = new FileOutputStream(new File(file, uuidName));

                    // 拷贝
                    IOUtils.copy(is, os);
                    // 释放资源
                    os.close();
                    is.close();
                    // 删除文件
                    fi.delete();

                    // 景点路径
                    map.put(key, "img/test/img" + "/" + uuidName);
                    // 1.封装route对象
                    Route route = new Route();
                    // 1.1手动加入时间
                    map.put("rdate", new Date());
                    BeanUtils.populate(route, map);

                    // 2.调用service
                    routeService.update(route);

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            throw new RuntimeException("保存商品失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/scenic.html");


    }

    /**
     * 添加景点信息
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void addpic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            //使用fileuload保存图片和景点信息。
            // 1.1map集合，存放信息
            Map<String, Object> map = new HashMap<>();

            // 1.2创建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 1.3核心上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析request
            List<FileItem> list = upload.parseRequest(request);


            for (FileItem fi : list) {
                // 获取name属性
                String key = fi.getFieldName();
                // 判断是否为普遍组件
                if (fi.isFormField()) {
                    // 普通的组件
                    map.put(key, fi.getString("utf-8"));
                } else {
                    // 文件
                    // 获取文件名称
                    String name = fi.getName();
                    // 获取真实名称
                    String realName = UploadUtils.getRealName(name);
                    // 调用UUID工具类给文件附上一个随机名称
                    String uuidName = UploadUtils.getUUIDName(realName);
                    // 随机目录
                    String dir = UploadUtils.getDir();
                    // 文件内容
                    InputStream is = fi.getInputStream();
                    // 创建输出流
                    // 获取small正式路径
                    String productPath = getServletContext().getRealPath("/img/test/size2");
                    File file = new File(productPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    FileOutputStream os = new FileOutputStream(new File(file, uuidName));

                    // 拷贝
                    IOUtils.copy(is, os);
                    // 释放资源
                    os.close();
                    is.close();
                    // 删除文件
                    fi.delete();

                    // 景点路径
                    map.put(key, "img/test/size2" + "/" + uuidName);
                    // 1.封装route对象

                    RouteImg routeImg = new RouteImg();
                    // 1.1手动加入时间

                    BeanUtils.populate(routeImg, map);

                    // 2.调用service
                    routeService.addpic(routeImg);
                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            throw new RuntimeException("保存商品失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        // 重定向进行页面跳转
        response.sendRedirect(request.getContextPath() + "/scenic.html");

    }

    /**
     * 更改轮播图
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void updateslide(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            // 使用fileuload保存图片和景点信息。
            // 1.1map集合，存放信息
            Map<String, Object> map = new HashMap<>();

            // 1.2创建工厂
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // 1.3核心上传对象
            ServletFileUpload upload = new ServletFileUpload(factory);
            // 解析request
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem fi : list) {
                // 获取name属性
                String key = fi.getFieldName();
                // 判断是否为普遍组件
                if (fi.isFormField()) {
                    // 普通的组件
                    map.put(key, fi.getString("utf-8"));
                } else {
                    // 文件
                    // 文件获取文件名称
                    String name = fi.getName();
                    // 获取真实名称
                    String realName = UploadUtils.getRealName(name);
                    //通过UUID获取随机名称
                    String uuidName = UploadUtils.getUUIDName(realName);
                    // 随机目录
                    String dir = UploadUtils.getDir();
                    // 文件内容
                    InputStream is = fi.getInputStream();
                    // 创建输出流
                    // 获取small正式路径
                    String productPath = getServletContext().getRealPath("/images");
                    File file = new File(productPath);
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    FileOutputStream os = new FileOutputStream(new File(file, uuidName));

                    // 拷贝
                    IOUtils.copy(is, os);
                    // 释放资源
                    os.close();
                    is.close();
                    // 删除文件
                    fi.delete();

                    // 景点路径
                    map.put(key, "images" + "/" + uuidName);
                    // 1.封装route对象

                    RouteBigimg routeBigimg = new RouteBigimg();

                    BeanUtils.populate(routeBigimg, map);
                    // 2.调用service
                    routeService.updateslide(routeBigimg);

                }
            }
        } catch (FileUploadException e) {
            e.printStackTrace();
            throw new RuntimeException("保存商品失败");
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        response.sendRedirect(request.getContextPath() + "/slideshow.html");
    }

}



