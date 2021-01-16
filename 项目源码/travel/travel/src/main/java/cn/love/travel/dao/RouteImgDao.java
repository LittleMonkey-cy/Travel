package cn.love.travel.dao;

import cn.love.travel.pojo.RouteImg;

import java.util.List;

public interface RouteImgDao {
    /**
     * 根据rid去查图片
     * @param rid 路线id
     * @return
     */
     List<RouteImg> findByRid(int rid);

    /**
     * 添加图片信息
     * @param routeImg
     */
    void add(RouteImg routeImg);


}
