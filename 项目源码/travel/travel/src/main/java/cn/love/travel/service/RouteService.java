package cn.love.travel.service;

import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.Route;
import cn.love.travel.pojo.RouteBigimg;
import cn.love.travel.pojo.RouteImg;

import java.util.List;

/**
* @author 陈钰
* @Description 线路业务逻辑接口方法
* @Date 2020/12/20
* @Param
* @return
**/
public interface RouteService {
    /**
     * 根据类别分页查询的方法
     * @param cid
     * @param currentPage
     * @param pageSize
     * @param rname
     * @return
     */
    PageBean<Route> pageQuery(int cid,int currentPage,int pageSize,String rname);

    /**
     * 根据rid查询景点详情
     * @param rid
     * @return
     */
    Route findOne(String rid);

    /**
     * 国内游展示
     * @return
     */
    List<Route> inlandOne();

    /**
     * 国外游展示
     * @return
     */
    List<Route> foreigndOne();

    /**
     *展示所有图片信息
     * @return
     */
    List<RouteBigimg> slideshow();

    /**
     * 删除路线
     * @param rid
     */
    void delroute(String rid);

    /**
     * 批量删除路线
     * @param rids
     */
    void delrouteall(String[] rids);

    /**
     * 增加景点
     * @param route
     */
    void add(Route route);

    /**
     * 修改景点
     * @param route
     */
    void update(Route route);

    /**
     * 添加图片
     * @param routeImg
     */
    void addpic(RouteImg routeImg);

    /**
     * 轮播图修改
     * @param routeBigimg
     */
    void updateslide(RouteBigimg routeBigimg);
}
