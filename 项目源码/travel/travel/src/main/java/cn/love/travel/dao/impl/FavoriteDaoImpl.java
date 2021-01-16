package cn.love.travel.dao.impl;

import cn.love.travel.dao.FavoriteDao;
import cn.love.travel.pojo.Favorite;
import cn.love.travel.pojo.Route;
import cn.love.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.Date;
import java.util.List;

/**
* @author 陈钰
* @Description 收藏接口实现类
* @Date  2020/12/20
* @Param
* @return
**/
public class FavoriteDaoImpl implements FavoriteDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     *
     * @param rid 路线id
     * @param uid 用户id
     * @return
     */
    @Override
    public Favorite findByRidAndUid(int rid, int uid) {
        Favorite favorite = null;
        try {
            String sql ="select * from tab_favorite where rid= ? and uid =?";
            favorite = template.queryForObject(sql, new BeanPropertyRowMapper<Favorite>(Favorite.class), rid, uid);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return favorite;

    }

    /**
     *
     * @param rid 路线id
     * @return
     */
    @Override
    public int findCountById(int rid) {
        String sql ="select count(*) from tab_favorite where rid= ? ";
        return template.queryForObject(sql,Integer.class,rid);
    }

    /**
     *
     * @param rid 路线id
     * @param uid 用户id
     */
    @Override
    public void addFavorite(int rid, int uid) {
        String sql ="insert into tab_favorite values(?,?,?)";
        template.update(sql,rid,new Date(),uid);
        String sql1="UPDATE tab_route SET tab_route.rcount=tab_route.rcount+1 WHERE rid=?";
        template.update(sql1,rid);
    }

    /**
     *
     * @param uid 用户id
     * @return
     */
    @Override
    /**
     * 通过用户的id显示该用户下的所有收藏信息
     * @param uid 用户id
     * @return 收藏集合
     */
    public List<Favorite> myFavorite(int uid) {
        String sql="SELECT * FROM tab_favorite WHERE uid=? ORDER BY tab_favorite.date DESC";
        List<Favorite> favorites = template.query(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),uid);
        return favorites;
    }

    /**
     *
     * @param uid 用户id
     * @return
     */
    @Override
    public int findTotalCount(int uid) {
        String sql = "select count(*) from tab_favorite where uid = ?";
        return template.queryForObject(sql,Integer.class,uid);
    }

    /**
     *
     * @param uid 用户id
     * @param start
     * @param pageSize 分页数
     * @return
     */
    @Override
    public List<Favorite> findByPage(int uid, int start, int pageSize) {
        String sql="select * from tab_favorite where uid =? ORDER BY tab_favorite.date DESC limit ?,?";
        return template.query(sql,new BeanPropertyRowMapper<Favorite>(Favorite.class),uid,start,pageSize);
    }

    /**
     *
     * @param rid
     */
    @Override
    public void del(int rid) {
        String sql=" DELETE FROM tab_favorite WHERE rid=?";
        template.update(sql,rid);
    }

    /**
     *收藏排行榜
     * @return
     */
    @Override
    public List<Route> top() {
        String sql="SELECT * FROM tab_route ORDER BY rcount DESC LIMIT 10";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    /**
     *最新景点
     * @return
     */
    @Override
    public List<Route> newRoute() {
        String sql="SELECT * FROM tab_route ORDER BY rdate DESC LIMIT 10";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }

    /**
     * 主题旅游
     * @return
     */
    @Override
    public List<Route> themeRoute() {
        String sql="SELECT * FROM tab_route WHERE isThemeTour=1 ORDER BY rcount DESC LIMIT 10 ";
        return template.query(sql,new BeanPropertyRowMapper<Route>(Route.class));
    }


}
