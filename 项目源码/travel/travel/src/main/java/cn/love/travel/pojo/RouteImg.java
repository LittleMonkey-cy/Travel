package cn.love.travel.pojo;

import java.io.Serializable;

/**
* @author 陈钰
* @Description 旅游路线图片实体类
* @Date 8:16 2020/12/20
* @Param
* @return
**/
public class RouteImg implements Serializable {
    // 实现了序列化接口
    /**
     * 商品图片id
     */
    private int rgid;
    /**
     * 旅游商品id
     */
    private int rid;
    /**
     * 详情商品大图
     */
    private String bigPic;
    /**
     * 详情商品小图
     */
    private String smallPic;

    /**
     * 无参构造方法
     */
    public RouteImg() {
    }

    /**
     * 有参构造方法
     * @param rgid 商品图片id
     * @param rid 路线id
     * @param bigPic 大图
     * @param smallPic 小图
     */
    public RouteImg(int rgid, int rid, String bigPic, String smallPic) {
        this.rgid = rgid;
        this.rid = rid;
        this.bigPic = bigPic;
        this.smallPic = smallPic;
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

    public String getSmallPic() {
        return smallPic;
    }

    public void setSmallPic(String smallPic) {
        this.smallPic = smallPic;
    }
}
