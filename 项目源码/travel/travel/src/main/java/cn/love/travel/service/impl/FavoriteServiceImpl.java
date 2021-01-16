package cn.love.travel.service.impl;

import cn.love.travel.dao.FavoriteDao;
import cn.love.travel.dao.RouteDao;
import cn.love.travel.dao.impl.FavoriteDaoImpl;
import cn.love.travel.dao.impl.RouteDaoImpl;
import cn.love.travel.pojo.Favorite;
import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.Route;
import cn.love.travel.service.FavoriteService;

import java.util.List;

/**
 * @author 陈钰
 * @Description 收藏页的业务逻辑层
 * @Date 2020/12/20
 * @Param
 * @return
 **/
public class FavoriteServiceImpl implements FavoriteService {
    // 实例化对象
    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private RouteDao routeDao = new RouteDaoImpl();

    /**
     * 判断用户是否收藏
     *
     * @param rid
     * @param uid
     * @return
     */
    @Override
    public boolean isFavorite(String rid, int uid) {
        Favorite favorite = favoriteDao.findByRidAndUid(Integer.parseInt(rid), uid);

        return favorite != null;
    }

    /**
     * 添加收藏
     *
     * @param rid
     * @param uid
     */
    @Override
    public void addFavorite(String rid, int uid) {
        favoriteDao.addFavorite(Integer.parseInt(rid), uid);
    }

    /**
     * 根据用户id查询该用户下的收藏
     *
     * @param uid
     * @return
     */
    @Override
    public List<Favorite> myFavorite(int uid) {

        List<Favorite> favorites = favoriteDao.myFavorite(uid);
        for (Favorite favorite : favorites) {
            Route route = routeDao.findOne(favorite.getRid());
            favorite.setRoute(route);
        }
        return favorites;
    }

    /**
     * 分页
     *
     * @param uid
     * @param currentPage
     * @param pageSize
     * @return
     */
    @Override
    public PageBean<Favorite> pageQuery(int uid, int currentPage, int pageSize) {
        // 实例化对象
        PageBean<Favorite> pb = new PageBean<Favorite>();

        // 设置当前页码
        pb.setCurrentPage(currentPage);
        // 设置每页显示条数
        pb.setPageSize(pageSize);
        // 总记录数
        int totalCount = favoriteDao.findTotalCount(uid);
        pb.setTotalCount(totalCount);

        // 设置当前页集合
        // 开始记录
        int start = (currentPage - 1) * pageSize;
        List<Favorite> list = favoriteDao.findByPage(uid, start, pageSize);
        for (Favorite favorite : list) {
            Route route = routeDao.findOne(favorite.getRid());
            favorite.setRoute(route);
        }
        pb.setList(list);

        // 设置总记录数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 删除
     *
     * @param rid
     */
    @Override
    public void delFavorite(String rid) {
        favoriteDao.del(Integer.parseInt(rid));
    }

    /**
     * 收藏排行榜
     *
     * @return
     */
    @Override
    public List<Route> topFavorite() {
        return favoriteDao.top();
    }

    /**
     * 最新景点
     *
     * @return
     */
    @Override
    public List<Route> newFavorite() {
        return favoriteDao.newRoute();
    }

    /**
     * 显示所有主题旅游信息
     *
     * @return
     */
    @Override
    public List<Route> themeFavorite() {
        return favoriteDao.themeRoute();
    }


}

