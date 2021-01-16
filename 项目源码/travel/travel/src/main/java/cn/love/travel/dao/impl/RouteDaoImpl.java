package cn.love.travel.dao.impl;

import cn.love.travel.dao.RouteDao;
import cn.love.travel.pojo.Route;
import cn.love.travel.pojo.RouteBigimg;
import cn.love.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author 陈钰
 * @Description 路线的接口实现类
 * @Date 10:45 2020/12/24
 * @Param
 * @return
 **/
public class RouteDaoImpl implements RouteDao {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 总页数
     *
     * @param cid   收藏id
     * @param rname 路线名称
     * @return
     */
    @Override
    public int findTotalCount(int cid, String rname) {
        // 拼写sql
        String sql = "select count(*) from tab_route where 1=1 ";
        StringBuilder sb = new StringBuilder(sql);

        List params = new ArrayList();//条件们
        // 判断参数是否有值
        if (cid != 0) {
            if (cid == 1) {
                sb.append(" and tickets !='免费'and tickets !='免费' ");
            } else if (cid == 2) {
                sb.append(" and isThemeTour =1 ");
            } else if (cid == 3) {
                sb.append(" and rcount >= 200 ");
            } else {
                sb.append(" and cid= ? ");
                params.add(cid);//添加？对应的值
            }
        }

        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");
            params.add("%" + rname + "%");
        }

        sql = sb.toString();


        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 页码查询
     * @param cid
     * @param start
     * @param pageSize
     * @param rname
     * @return
     */
    @Override
    public List<Route> findByPage(int cid, int start, int pageSize, String rname) {

        // String sql = "select * from tab_route where cid = ? and rname like ?  limit ? , ?";
        String sql = " select * from tab_route where 1 = 1 ";
        // 定义sql模板
        StringBuilder sb = new StringBuilder(sql);

        // 查询条件
        List params = new ArrayList();
        //2.判断参数是否有值

        if (cid != 0) {
            if (cid == 1) {
                sb.append(" and tickets !='免费' ");
            } else if (cid == 2) {
                sb.append(" and isThemeTour =1 ");
            } else if (cid == 3) {
                sb.append(" and rcount >= 200 ");
            } else {
                sb.append(" and cid= ? ");
                params.add(cid);//添加？对应的值
            }

        }

        if (rname != null && rname.length() > 0) {
            sb.append(" and rname like ? ");

            params.add("%" + rname + "%");
        }
        sb.append(" limit ? , ? ");//分页条件

        sql = sb.toString();

        params.add(start);
        params.add(pageSize);


        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class), params.toArray());
    }

    /**
     * 根据rid查询一个景点详情
      * @param rid  路线id
     * @return
     */
    @Override
    public Route findOne(int rid) {
        String sql = "select * from tab_route where rid= ? ";
        return template.queryForObject(sql, new BeanPropertyRowMapper<Route>(Route.class), rid);
    }

    /**
     * 国内游信息展示
     * @return
     */
    @Override
    public List<Route> inlandOne() {
        String sql = "SELECT * FROM tab_route WHERE cid=5 ORDER BY rcount DESC LIMIT 8";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
    }

    /**
     * 国外游信息展示
     * @return
     */
    @Override
    public List<Route> foreigndOne() {
        String sql = "SELECT * FROM tab_route WHERE cid=4 ORDER BY rcount DESC LIMIT 8";
        return template.query(sql, new BeanPropertyRowMapper<Route>(Route.class));
    }

    /**
     * 图片
     * @return
     */
    @Override
    public List<RouteBigimg> slideshow() {
        String sql = "SELECT* FROM tab_route_bigimg";
        return template.query(sql, new BeanPropertyRowMapper<RouteBigimg>(RouteBigimg.class));
    }

    /**
     * 根据路线id删除路线信息
     * @param rid
     */
    @Override
    public void delroute(int rid) {
         // 进行外键信息查询，如果下边有其他外键则不能删除
        String sql1 = "SET FOREIGN_KEY_CHECKS = 0";
        String sql = "delete from tab_route where rid=?";
        String sql2 = "SET FOREIGN_KEY_CHECKS = 1";
        template.update(sql1);
        template.update(sql, rid);
        template.update(sql2);

    }

    /**
     * 添加景点信息
     * @param route
     */
    @Override
    public void add(Route route) {
        String sql = "insert into tab_route(rname,price,routeIntroduce,rdate,isThemeTour,cid,rimage,times,telephone,traffic,tickets,development,introduction) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
        template.update(sql, route.getRname(), route.getPrice(), route.getRouteIntroduce(), new Date(),
                route.getIsThemeTour(), route.getCid(), route.getRimage(), route.getTimes(),
                route.getTelephone(), route.getTraffic(), route.getTickets(), route.getDevelopment(), route.getIntroduction());

    }

    /**
     * 修改路线信息
     * @param route 路线对象
     */
    @Override
    public void update(Route route) {
        String sql = "update tab_route set rname=? ,price=?,routeIntroduce=?, isThemeTour=?,rcount=?,rimage=?,times=?," +
                "telephone=?,traffic=?,tickets=?,development=?,introduction=? where rid=?";
        template.update(sql, route.getRname(), route.getPrice(), route.getRouteIntroduce(),
                route.getIsThemeTour(), route.getRcount(), route.getRimage(), route.getTimes(),
                route.getTelephone(), route.getTraffic(), route.getTickets(), route.getDevelopment(), route.getIntroduction(), route.getRid());
    }

}
