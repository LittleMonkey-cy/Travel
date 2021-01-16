package cn.love.travel.dao.impl;

import cn.love.travel.dao.RouteImgDao;
import cn.love.travel.pojo.RouteImg;
import cn.love.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
* @author 陈钰
* @Description 图片接口的实现类
* @Date  2020/12/24
* @Param
* @return
**/
public class RouteImgDaoImpl implements RouteImgDao {
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据路线id查询路线信息
     * @param rid 路线id
     * @return
     */
    @Override
    public List<RouteImg> findByRid(int rid) {
        String sql ="select * from tab_route_img where rid=?";
        return template.query(sql,new BeanPropertyRowMapper<RouteImg>(RouteImg.class),rid);
    }

    /**
     * 添加图片信息
     * @param routeImg
     */
    @Override
    public void add(RouteImg routeImg) {
        String sql="insert into tab_route_img(rid,bigPic,smallPic) values (?,?,?)";
        String sql2="DELETE FROM tab_route_img WHERE rid=? AND smallPic IS NULL";
        template.update(sql,routeImg.getRid(),routeImg.getBigPic(),routeImg.getSmallPic());
        template.update(sql2,routeImg.getRid());
    }


}
