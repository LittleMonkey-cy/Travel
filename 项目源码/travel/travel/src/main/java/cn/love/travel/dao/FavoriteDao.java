package cn.love.travel.dao;

import cn.love.travel.pojo.Favorite;
import cn.love.travel.pojo.Route;

import java.util.List;
/**
* @author 陈钰
* @Description 收藏接口
* @Date 10:39 2020/12/24
* @Param
* @return
**/
public interface FavoriteDao {
    /**
     * 判断用户是否收藏路线
     * @param rid 路线id
     * @param uid 用户id
     * @return
     */
    Favorite findByRidAndUid(int rid, int uid);

    /**
     * 收藏次数
     * @param rid 路线id
     * @return
     */
    int findCountById(int rid);

    /**
     * 添加收藏
     * @param rid 路线id
     * @param uid 用户id
     */
    void addFavorite(int rid, int uid);

    /**
     * 通过用户的id显示该用户下的所有收藏信息
     * @param uid 用户id
     * @return 收藏集合
     */
    List<Favorite> myFavorite(int uid);

    /**
     * 查询计数
     * @param uid 用户id
     * @return
     */
    int findTotalCount(int uid);

    /**
     * 每页显示的数据
     * @param uid 用户id
     * @param start
     * @param pageSize 分页数
     * @return
     */
    List<Favorite> findByPage(int uid , int start , int pageSize);

    /**
     * 删除收藏
     * @param rid
     */
    void del(int rid);

    /**
     * 收藏排行榜
     * @return
     */
    List<Route> top();

    /**
     * 最新景点
     * @return
     */
    List<Route> newRoute();

    /**
     * 主题路线
     * @return
     */
    List<Route> themeRoute();
}
