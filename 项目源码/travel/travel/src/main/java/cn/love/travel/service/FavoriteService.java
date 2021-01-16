package cn.love.travel.service;

import cn.love.travel.pojo.Favorite;
import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.Route;

import java.util.List;

/**
 * @author 陈钰
 * @Description 用户收藏页面的业务逻辑层
 * @Date 2020/12/20
 * @Param
 * @return
 **/
public interface FavoriteService {


    /**
     * 用户是否收藏景点
     * @param rid
     * @param uid
     * @return
     */
    boolean isFavorite(String rid, int uid);

    /**
     * 添加收藏
     * @param rid
     * @param uid
     */
    void addFavorite(String rid, int uid);

    /**
     * 根据用户id查询该用户下的收藏
     * @param uid
     * @return
     */
     List<Favorite> myFavorite(int uid);

    /**
     * 分页
     * @param uid
     * @param currentPage
     * @param pageSize
     * @return
     */
    PageBean<Favorite> pageQuery(int uid, int currentPage, int pageSize);

    /**
     * 删除收藏
     * @param rid
     */
    void delFavorite(String rid);

    /**
     * 收藏榜单
     * @return
     */
    List<Route> topFavorite();

    /**
     * 最新景点
     * @return
     */
    List<Route> newFavorite();

    /**
     * 是否为主题旅游
     * @return
     */
    List<Route> themeFavorite();
}
