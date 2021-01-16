package cn.love.travel.pojo;

import java.io.Serializable;
import java.util.List;

/**
* @author 旅游路线（商品）实体类
* @Description 
* @Date 7:55 2020/12/20
* @Param 
* @return 
**/
public class Route implements Serializable {
    /**
     * 路线id
     */
    private int rid;
    /**
     * 路线名称
     */
    private String rname;
    /**
     * 价格
     */
    private double price;
    /**
     * 路线介绍
     */
    private String routeIntroduce;
    /**
     * 是否上架的标志0代表没有上架，1代表是上架
     */
    private String rflag;
    /**
     * 上架时间
     */
    private String rdate;
    /**
     * 是否主题旅游，必输，0代表不是，1代表是
     */
    private String isThemeTour;
    /**
     * 收藏数量
     */
    private int rcount;
    /**
     * 所属分类
     */
    private int cid;
    /**
     * 缩略图
     */
    private String rimage;
    /**
     * 所属商家
     */
    private int sid;
    /**
     * 抓取数据的来源id
     */
    private String sourceId;
    /**
     * 用时
     */
    private String times;
    /**
     * 电话
     */
    private String telephone;
    /**
     * 交通
     */
    private String traffic;
    /**
     * 门票
     */
    private String tickets;
    /**
     * 开放时间
     */
    private String development;
    /**
     * 景点介绍
     */
    private String introduction;
    /**
     * 所属分类
     */
    private Category category;
    /**
     * 所属商家
     */
    private Seller seller;
    /**
     * 商品详情图片列表
     */
    private List<RouteImg> routeImgList;


    /**
     * 无参构造方法
     */
    public Route(){}

    /**
     * 有参构造方法
     * @param rid 路线id
     * @param rname 路线名称
     * @param price 价格
     * @param routeIntroduce 路线简介
     * @param rflag 是否上架
     * @param rdate 上架日期
     * @param isThemeTour 是否为主题旅游
     * @param rcount 收藏数量
     * @param cid 分类编号
     * @param rimage 商品图片
     * @param sid 商家编号
     * @param sourceId 抓取数据来源的id
     */
    public Route(int rid, String rname, double price, String routeIntroduce, String rflag, String rdate, String isThemeTour, int rcount, int cid, String rimage, int sid, String sourceId, String times, String telephone, String traffic, String tickets, String development, String introduction) {
        this.rid = rid;
        this.rname = rname;
        this.price = price;
        this.routeIntroduce = routeIntroduce;
        this.rflag = rflag;
        this.rdate = rdate;
        this.isThemeTour = isThemeTour;
        this.rcount = rcount;
        this.cid = cid;
        this.rimage = rimage;
        this.sid = sid;
        this.sourceId = sourceId;
        this.times = times;
        this.telephone = telephone;
        this.traffic = traffic;
        this.tickets = tickets;
        this.development = development;
        this.introduction = introduction;
    }


    public List<RouteImg> getRouteImgList() {
        return routeImgList;
    }

    public void setRouteImgList(List<RouteImg> routeImgList) {
        this.routeImgList = routeImgList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getRouteIntroduce() {
        return routeIntroduce;
    }

    public void setRouteIntroduce(String routeIntroduce) {
        this.routeIntroduce = routeIntroduce;
    }

    public String getRflag() {
        return rflag;
    }

    public void setRflag(String rflag) {
        this.rflag = rflag;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getIsThemeTour() {
        return isThemeTour;
    }

    public void setIsThemeTour(String isThemeTour) {
        this.isThemeTour = isThemeTour;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getRimage() {
        return rimage;
    }

    public void setRimage(String rimage) {
        this.rimage = rimage;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }
    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTraffic() {
        return traffic;
    }

    public void setTraffic(String traffic) {
        this.traffic = traffic;
    }

    public String getTickets() {
        return tickets;
    }

    public void setTickets(String tickets) {
        this.tickets = tickets;
    }

    public String getDevelopment() {
        return development;
    }

    public void setDevelopment(String development) {
        this.development = development;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }
}
