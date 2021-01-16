package cn.love.travel.service.impl;


import cn.love.travel.pojo.*;
import cn.love.travel.pojo.Category;
import cn.love.travel.service.RouteService;
import cn.love.travel.dao.*;
import cn.love.travel.dao.impl.*;

import java.util.List;

/**
* @author 陈钰
* @Description 路线业务逻辑层接口实现类
* @Date 2020/12/20
* @Param
* @return
**/
public class RouteServiceImpl implements RouteService {
    // 实例化对象
    private RouteDao routeDao = new RouteDaoImpl();
    private RouteImgDao routeImgDao = new RouteImgDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private RouteSlideshowDao routeSlideshowDao=new RouteSlideshowDaoImpl();

    /**
     * 根据类别分页查询的方法
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        //封装PageBean
        PageBean<Route> pb = new PageBean<Route>();
        //设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);

        //设置总记录数
        int totalCount = routeDao.findTotalCount(cid, rname);
        pb.setTotalCount(totalCount);
        //设置当前页显示的数据集合
        int start = (currentPage - 1) * pageSize;//开始的记录数
        List<Route> list = routeDao.findByPage(cid, start, pageSize, rname);
        pb.setList(list);

        //设置总页数 = 总记录数/每页显示条数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 根据rid查询景点详情
     * @param rid
     * @return
     */
    @Override
    public Route findOne(String rid) {
        // 根据rid去route表中查询对象
        Route route = routeDao.findOne(Integer.parseInt(rid));
        // 根据route的id，查询图片信息
        List<RouteImg> routeImgList = routeImgDao.findByRid(route.getRid());

        route.setRouteImgList(routeImgList);
        // 根据cid，访问到cname 查询category对象
        Category category = categoryDao.findById(route.getCid());
        route.setCategory(category);

        return route;
    }


    /**
     * 国内游展示
     * @return
     */
    @Override
    public List<Route> inlandOne() {
        return routeDao.inlandOne();
    }

    /**
     * 国外游
     * @return
     */
    @Override
    public List<Route> foreigndOne() {
        return routeDao.foreigndOne();
    }

    @Override
    public List<RouteBigimg> slideshow() {
        return routeDao.slideshow();
    }

    /**
     * 删除一个
     * @param rid 路线id
     */
    @Override
    public void delroute(String rid) {
        routeDao.delroute(Integer.parseInt(rid));
    }

    /**
     * 删除选中
     * @param rids
     */
    @Override
    public void delrouteall(String[] rids) {
        for (String rid : rids) {
            routeDao.delroute(Integer.parseInt(rid));
        }
    }

    /**
     * 添加景点
     * @param route
     */
    @Override
    public void add(Route route) {
        routeDao.add(route);
    }

    /**
     * 修改景点
     * @param route
     */
    @Override
    public void update(Route route) {
        routeDao.update(route);
    }

    /**
     * 添加图片
     * @param routeImg
     */
    @Override
    public void addpic(RouteImg routeImg) {
        routeImgDao.add(routeImg);
    }

    /**
     * 轮播图修改
     * @param routeBigimg
     */
    @Override
    public void updateslide(RouteBigimg routeBigimg) {
        routeSlideshowDao.update(routeBigimg);
    }


}
