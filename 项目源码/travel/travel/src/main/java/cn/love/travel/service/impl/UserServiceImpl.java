package cn.love.travel.service.impl;

import cn.love.travel.dao.UserDao;
import cn.love.travel.dao.impl.UserDaoImpl;
import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.User;
import cn.love.travel.service.UserService;
import cn.love.travel.util.MailUtils;
import cn.love.travel.util.UuidUtil;

import java.util.List;

public class UserServiceImpl implements UserService {
    // 实例化对象
    private UserDao userDao = new UserDaoImpl();

    /**
     * 注册用户
     *
     * @param user 用户对象
     * @return
     */
    @Override
    public boolean regist(User user) {
       // 1.根据用户名查询用户对象
        User u = userDao.findByUsername(user.getUsername());
        if (u != null) {
            //用户名存在
            return false;
        }
       // 2.保存用户信息
       // 设置唯一的激活码，为激活邮箱用
        user.setCode(UuidUtil.getUuid());
       // 2.2设置激活码的状态
        user.setStatus("N");
        userDao.save(user);
        // 3.邮箱激活的发信
        String content = "<a href='http://localhost:8080/travel/activeUserServlet?code=" + user.getCode() + "'>点击激活【爱旅游】</a>";
        MailUtils.sendMail(user.getEmail(), content, "激活邮件");
        return true;
    }


    /**
     * 激活用户邮箱 激活用户邮箱
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        // 根据激活码查询用户
        User user = userDao.findByCode(code);
        if (user != null) {
            // 修改激活状态
            userDao.updateStatus(user);
            return true;
        } else {
            return false;
        }

    }


    /**
     * 登录验证
     * @param user
     * @return
     */
    @Override
    public User login(User user) {
        return userDao.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    /**
     * 根据用户id查看个人中心
     * @param uid
     * @return
     */
    @Override
    public User personfind(int uid) {
        return userDao.personal(uid);
    }

    /**
     * 个人中心更改
     * @param user
     * @param uid
     */
    @Override
    public void Personupdate(User user, int uid) {
        userDao.Personupdate(user, uid);
    }

    /**
     * 分页
     * @param currentPage
     * @param pageSize
     * @param uname
     * @return
     */
    @Override
    public PageBean<User> pageQuery(int currentPage, int pageSize, String uname) {
        // 实例化
        PageBean<User> pb = new PageBean<User>();

        // 设置当前页码
        pb.setCurrentPage(currentPage);
        // 设置每页显示条数
        pb.setPageSize(pageSize);
        // 总记录数
        int totalCount = userDao.findTotalCount(uname);
        pb.setTotalCount(totalCount);

        // 设置当前页集合
        // 开始记录
        int start = (currentPage - 1) * pageSize;
        List<User> list = userDao.findByPage(start, pageSize, uname);
        pb.setList(list);

        // 设置总记录数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : (totalCount / pageSize) + 1;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 批量删除
     * @param uids
     */
    @Override
    public void delall(String[] uids) {
        // 遍历
        for(String uid:uids){
            // 调用dao删除
            userDao.del(Integer.parseInt(uid));
        }
    }

    /**
     * 根据用户ID删除单个用户信息
     * @param uid 用户id
     */
    @Override
    public void delUser(String uid) {
        userDao.del(Integer.parseInt(uid));
    }

}
