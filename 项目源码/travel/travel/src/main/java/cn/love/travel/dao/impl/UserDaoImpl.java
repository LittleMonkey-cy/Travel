package cn.love.travel.dao.impl;

import cn.love.travel.dao.UserDao;
import cn.love.travel.pojo.User;
import cn.love.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    // 实例化对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 根据用户名查询用户信息（查重）
     *
     * @param username
     * @return
     */
    @Override
    public User findByUsername(String username) {
        User user = null;
        // 拼写sql
        try {
            String sql = "select * from tab_user where username=?";
            //执行
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username);
        } catch (DataAccessException e) {

        }
        return user;
    }

    /**
     * 添加用户信息
     * @param user
     */
    @Override
    public void save(User user) {
        // 拼写sql语句
        String sql = "insert into tab_user(username,password,name,birthday,sex,telephone,email,status,code) values(?,?,?,?,?,?,?,?,?)";
        // 执行sql
        template.update(sql, user.getUsername(),
                user.getPassword(),
                user.getName(),
                user.getBirthday(),
                user.getSex(),
                user.getTelephone(),
                user.getEmail(),
                user.getStatus(),
                user.getCode()
        );
    }

    /**
     * 根据激活码查询用户对象
     * @param code
     * @return
     */
    @Override
    public User findByCode(String code) {
        User user = new User();
        try {
            String sql = "select * from tab_user where code=?";
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), code);
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
        return user;
    }

    /**
     * 修改激活状态
     * @param user
     */
    @Override
    public void updateStatus(User user) {
        String sql = "update tab_user set status='Y' where uid=?";
        template.update(sql, user.getUid());

    }

    /**
     * 用户登录，根据用户名和密码查询
     * @param username
     * @param password
     * @return
     */
    @Override
    public User findByUsernameAndPassword(String username, String password) {
        User user = null;
        // 拼写sql语句
        try {
            String sql = "select * from tab_user where username=? and password=?";
            // 执行
            user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
        } catch (DataAccessException e) {

        }
        return user;
    }

    /**
     * 个人中心
     * @param uid
     * @return
     */
    @Override
    public User personal(int uid) {
        String sql = "select * from tab_user where uid=?";
        return template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), uid);
    }

    /**
     * 修改个人中心
     * @param user
     * @param uid
     */
    @Override
    public void Personupdate(User user, int uid) {
        String sql = "update tab_user set username=?,password=?,name=?,birthday=?,sex=?,telephone=?,email=? where uid=?";
        template.update(sql, user.getUsername(), user.getPassword(), user.getName(), user.getBirthday(), user.getSex(), user.getTelephone(), user.getEmail(), uid);
    }

    /**
     * 查询用户总数（根据用户名模糊查询）
     * @param name
     * @return
     */
    @Override
    public int findTotalCount(String name) {
                // 1.定义sql模板
        String sql = "select count(*) from  tab_user where 1=1 ";
        StringBuilder s = new StringBuilder(sql);
        List params = new ArrayList();
        // 2.判断参数是否有值
        if (name != null && name.length() > 0) {
            s.append(" and name like ?");
            params.add("%" + name + "%");
        }
        sql = s.toString();

        return template.queryForObject(sql, Integer.class, params.toArray());
    }

    /**
     * 分页
     * @param start
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public List<User> findByPage(int start, int pageSize, String name) {

        String sql = "select * from tab_user where 1=1 ";

        StringBuilder s = new StringBuilder(sql);

        List params = new ArrayList();//条件
        // 2.判断参数是否有值
        if (name != null && name.length() > 0) {
            s.append(" and name like ?");
            params.add("%" + name + "%");
        }
        // 分页条件
        s.append(" limit ? , ?");
        sql = s.toString();
        params.add(start);
        params.add(pageSize);

        return template.query(sql, new BeanPropertyRowMapper<User>(User.class), params.toArray());
    }

    /**
     * 根据用户id删除用户信息
     *
     * @param uid
     */
    @Override
    public void del(int uid) {
        String sql = "DELETE  from tab_user Where uid=?";
        //执行
        template.update(sql, uid);
    }

}
