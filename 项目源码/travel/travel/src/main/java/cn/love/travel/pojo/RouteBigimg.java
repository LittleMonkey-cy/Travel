package cn.love.travel.pojo;

import java.io.Serializable;

/**
* @author 陈钰
* @Description 旅游路线首页大图
* @Date 8:11 2020/12/20
* @Param
* @return
**/
public class RouteBigimg implements Serializable  {
    // 实现了序列化接口

    /**
     * 图片编号
     */
    private  int rgid ;
    /**
     * 路线编号
     */
    private int rid;
    /**
     * 大图
     */
    private String bigPic;
    /**
     * 旅游路线对象
     */
    private Route route;

    /**
     * 无参构造方法
     */
    public RouteBigimg(){}

    /**
     * 有参构造
     * @param rgid
     * @param rid
     * @param bigPic
     * @param route
     */
    public RouteBigimg(int rgid, int rid, String bigPic, Route route) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.route = route;
    }
    public int getRgid() {
        return rgid;
    }

    public void setRgid(int rgid) {
        this.rgid = rgid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getBigPic() {
        return bigPic;
    }

    public void setBigPic(String bigPic) {
        this.bigPic = bigPic;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }


}
