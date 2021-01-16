package cn.love.travel.dao;

import cn.love.travel.pojo.Route;
import cn.love.travel.pojo.RouteBigimg;

import java.util.List;

/**
* @author 陈钰
* @Description 路线的接口方法类
* @Date  2020/12/20
* @Param
* @return
**/
public interface RouteDao {
    /**
     * 通过路线id查询路线信息
     * @param rid  路线id
     * @return
     */
     Route findOne(int rid);

    /**
     * 查询当前页的数据集合
     * @param cid
     * @param rname
     * @return
     */
     int findTotalCount(int cid,String rname);
     List<Route> findByPage(int cid , int start , int pageSize,String rname);

    /**
     * 显示所有国内游的信息
     * @return
     */
    List<Route> inlandOne();

    /**
     * 显示所有国外游的信息
     * @return
     */
    List<Route> foreigndOne();

    /**
     * 显示所有的图片信息
     * @return
     */
    List<RouteBigimg> slideshow();

    /**
     * 根据路线id
     * @param rid
     */
    void delroute(int rid);

    /**
     * 添加景点信息
     * @param route
     */
    void add(Route route);

    /**
     * 修改景点信息
     * @param route
     */
    void update(Route route);
}
