package cn.love.travel.service;

import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.User;

public interface UserService {
    /**
     * 注册用户
     * @param user 用户对象
     * @return
     */
    boolean regist(User user);

    /**
     * 判断是否激活
     * @param code
     * @return
     */
    boolean active(String code);

    /**
     * 登录
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 显示个人中心信息
     * @param uid
     * @return
     */
    User personfind(int uid);

    /**
     * 个人中心更改
     * @param user
     * @param uid
     */
    void Personupdate(User user, int uid);

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    PageBean<User> pageQuery(int currentPage, int pageSize, String name);

    /**
     * 删除用户集合
     * @param uids
     */
    void delall(String[] uids);

    /**
     * 删除单个用户
     * @param uid 用户id
     */
    void delUser(String uid);
}
