package cn.love.travel.pojo;

import java.io.Serializable;

/**
* @author 陈钰
* @Description 收藏实体类
* @Date 7:47 2020/12/20
* @Param
* @return
**/
public class Favorite implements Serializable {
    /**
     * 路线id
     */
    private int rid;
    /**
     * 旅游路线对象（外键）
     */
    private Route route;
    /**
     * 收藏时间
     */
    private String date;
    /**
     * 所属用户（外键）
     */
    private User user;



    /**
     * 无参构造方法
     */
    public Favorite() {
    }

    /**
     * 有参构造方法
     * @param route 路线对象
     * @param date 收藏时间
     * @param user 用户对象
     */
    public Favorite(Route route, int rid,String date, User user) {
        this.route = route;
        this.rid=rid;
        this.date = date;
        this.user = user;
    }

    public Route getRoute() {
        return route;
    }

    public  void setRoute(Route route) {
        this.route = route;
    }
    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
