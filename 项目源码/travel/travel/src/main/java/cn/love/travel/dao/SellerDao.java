package cn.love.travel.dao;

import cn.love.travel.pojo.Seller;

import java.util.List;

/**
* @author 陈钰
* @Description 管理员的抽象方法接口
* @Date 8:29 2020/12/20
* @Param
* @return
**/
public interface SellerDao {
    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    Seller login(String username, String password);

    /**
     * 管理员信息查询（显示所有管理员信息）
     * @return 管理员信息集合
     */
    List<Seller> listadmin();

    /**
     * 根据用户名进行查重验证
     * @param username 用户名
     * @return 管理员实体类对象
     */
    Seller findByUserName(String username);

    /**
     *添加管理员
     * @param seller 管理员实体对象
     */
    void addadmin(Seller seller);

    /**
     * 根据管理员id删除管理员信息
     * @param sid 管理员id
     */
    void del(int sid);

    /**
     * 根据管理员id查询管理员信息
     * @param sid 管理员id
     * @return
     */
    Seller findOneadmin(int sid);

    /**
     * 用户更改
     * @param seller 管理员对象
     */
    void update(Seller seller);

    /**
     * 总页数
     * @param name
     * @return
     */
    int findTotalCount(String name);

    /**
     * 当前页集合
     * @param start
     * @param pageSize
     * @param name
     * @return
     */
    List<Seller> findByPage(int start, int pageSize,String name);

    /**
     *更改个人中心
     * @param seller
     * @param sid
     */
    void personupdate(Seller seller, int sid);

}
