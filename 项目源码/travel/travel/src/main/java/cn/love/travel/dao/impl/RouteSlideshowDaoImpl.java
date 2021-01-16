package cn.love.travel.dao.impl;

import cn.love.travel.dao.RouteSlideshowDao;
import cn.love.travel.pojo.RouteBigimg;
import cn.love.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

public class RouteSlideshowDaoImpl implements RouteSlideshowDao {
    // 实例化对象
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 修改录播图
     * @param routeBigimg
     */
    @Override
    public void update(RouteBigimg routeBigimg) {
        String sql="update tab_route_bigimg set bigPic=? where rid=? ";
        template.update(sql,routeBigimg.getBigPic(),routeBigimg.getRid());
    }
}
