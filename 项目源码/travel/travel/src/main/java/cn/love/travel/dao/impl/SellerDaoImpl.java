package cn.love.travel.dao.impl;

import cn.love.travel.dao.SellerDao;
import cn.love.travel.pojo.Seller;
import cn.love.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class SellerDaoImpl implements SellerDao {
    // 没有使用basedao而是运用了jdbcutils（jdbc工具类）
    // 实例化JDBCUtils对象
    private JdbcTemplate template=new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     * 管理员登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @Override
    public Seller login(String username, String password) {
        Seller seller=null;
        //定义sql
        try {
            String sql = "select * from tab_seller where username=? and password = ?";
            //执行
            seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),username,password);
        }catch (DataAccessException e){
        }
        return seller;
    }

    /**
     * 管理员信息查询（显示所有管理员信息）
     * @return
     */
    @Override
    public List<Seller> listadmin() {
        String sql = "select * from tab_seller";
        //执行
        return  template.query(sql, new BeanPropertyRowMapper<Seller>(Seller.class));
}

    /**
     * 根据用户名进行查重验证
     * @param username 用户名
     * @return
     */
    @Override
    public Seller findByUserName(String username) {

        Seller seller=null;
        //定义sql
        try {
            String sql = "select * from tab_seller where username=?";
            //执行
            seller = template.queryForObject(sql, new BeanPropertyRowMapper<Seller>(Seller.class),username);
        }catch (DataAccessException e){

        }
        return seller;
    }

    /**
     * 添加管理员
     * @param seller 管理员实体对象
     */
    @Override
    public void addadmin(Seller seller) {
        String sql="insert into tab_seller(username,password,name,email,consphone,sex,birthday) values(?,?,?,?,?,?,?)";
        //执行
        template.update(sql,seller.getUsername(),seller.getPassword(),
                seller.getName(),seller.getEmail(),seller.getConsphone()
        ,seller.getSex(),seller.getBirthday());

    }

    /**
     * 根据管理员id删除管理员信息
     * @param sid 管理员id
     */
    @Override
    public void del(int sid) {
        String sql="DELETE FROM tab_seller WHERE sid=?";
        //执行
        template.update(sql,sid);
    }

    /**
     * 根据管理员id查询管理员信息
     * @param sid 管理员id
     * @return
     */
    @Override
    public Seller findOneadmin(int sid) {
        String sql="select * from tab_seller where sid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Seller>(Seller.class),sid);
    }

    /**
     * 更改管理员信息
     * @param seller 管理员对象
     */
    @Override
    public void update(Seller seller) {
        String sql="update tab_seller set username=?,name=?,email=?,consphone=?,sex=?,birthday=? where sid=?";
        template.update(sql,seller.getUsername(),seller.getName(),seller.getEmail(),seller.getConsphone(),seller.getSex(),
                seller.getBirthday(),seller.getSid());
    }

    /**
     * 总页数
     * @param name
     * @return
     */
    @Override
    public int findTotalCount(String name) {
        // 拼写sql语句
        String sql="select count(*) from  tab_seller where 1=1 ";
        StringBuilder s=new StringBuilder(sql);
        List params=new ArrayList();//条件
        // 2.判断参数是否有值
        if (name != null && name.length()>0){
            s.append(" and name like ?");
            params.add("%"+name+"%");
        }
        sql = s.toString();

        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    /**
     * 当前页集合
     * @param start
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public List<Seller> findByPage(int start, int pageSize,String name) {
        //最后拼接后的字符串 String sql="select * from tab_seller limit ?,?";
        // 拼写sql语句
        String sql="select * from tab_seller where 1=1 ";

        StringBuilder s=new StringBuilder(sql);

        List params=new ArrayList();//条件
       // 判断参数是否有值
        if (name != null && name.length()>0){
            s.append(" and name like ?");
            params.add("%"+name+"%");
        }
        // 分页条件
        s.append(" limit ? , ?");
        sql = s.toString();
        params.add(start);
        params.add(pageSize);

        return template.query(sql,new BeanPropertyRowMapper<Seller>(Seller.class),params.toArray());
    }

    /**
     * 更改个人中心
     * @param seller
     * @param sid
     */
    @Override
    public void personupdate(Seller seller, int sid) {
        String sql="update tab_seller set username=?,password=?,name=?,email=?,consphone=?,sex=?,birthday=? where sid=?";
        template.update(sql,seller.getUsername(),seller.getPassword(),seller.getName(),seller.getEmail(),seller.getConsphone(),seller.getSex(),
                seller.getBirthday(),sid);
    }


}
