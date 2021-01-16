package cn.love.travel.service;

import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.Seller;

import java.util.List;

public interface AdminService {
    /**
     * 管理员登录
     * @param seller
     * @return
     */
    Seller adminlogin(Seller seller);

    /**
     * 管理员信息查询
     * @return
     */
    List<Seller> listadmin();

    /**
     * 管理员添加
     * @param seller
     * @return
     */
    Boolean addadmin(Seller seller);

    /**
     * 管理员删除
     * @param sid
     */
    void deladmin(String sid);

    /**
     * 管理员更改时查询功能
     * @param sid
     * @return
     */
    Seller findOneadmin(String sid);

    /**
     * 修改管理员信息
     * @param seller
     */
    void update(Seller seller);

    /**
     * 批量删除
     * @param sids
     */
    void delall(String[] sids);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    PageBean<Seller> pageQuery(int currentPage, int pageSize,String name);

    /**
     * 修改个人中心
     * @param seller
     * @param sid
     */
    void Personupdate(Seller seller, int sid);

    /**
     *查询所有的信息
     * @param sid
     * @return
     */
    Seller personfind(int sid);
}
