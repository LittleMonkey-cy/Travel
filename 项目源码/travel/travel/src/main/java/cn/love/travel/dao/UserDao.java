package cn.love.travel.dao;

import cn.love.travel.pojo.User;

import java.util.List;

public interface UserDao {
    /**
     * 根据用户名查询用户信息
     * @param username
     * @return
     */
    User findByUsername(String username);

    /**
     * 添加用户信息
     * @param user
     */
    void save(User user);

    /**
     * 查找用户的激活码
     * @param code
     * @return
     */
    User findByCode(String code);

    /**
     * 更新用户激活状态
     * @param user
     */
    void updateStatus(User user);

    /**
     * 根据用户id删除用户信息
     * @param uid
     */
    void del(int uid);

    /**
     * 用户登录，根据用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    User findByUsernameAndPassword(String username, String password);

    /**
     * 个人中心
     * @param uid
     * @return
     */
    User personal(int uid);

    //个人中心更改
    void Personupdate(User user,int uid);
    //总页数
    int findTotalCount(String name);
    //当前页集合
    List<User> findByPage(int start, int pageSize, String name);
}
