package cn.love.travel.dao.impl;

import cn.love.travel.dao.CategoryDao;
import cn.love.travel.pojo.Category;
import cn.love.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

/**
* @author 陈钰
* @Description 类别接口实现类
* @Date 8:56 2020/12/24
* @Param
* @return
**/
public class CategoryDaoImpl implements CategoryDao {
    // 实例化对象
    private JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    /**
     *
     * @return 显示所有类别信息
     */
    @Override
    public List<Category> findAll() {
        String sql = "select * from tab_category order by cid" ;
        return template.query(sql, new BeanPropertyRowMapper<Category>(Category.class));

    }

    /**
     * 通过类别id查找类别信息
     * @param cid 类别id
     * @return
     */
    @Override
    public Category findById(int cid) {
        String sql = "select * from tab_category where cid=?" ;
        return template.queryForObject(sql, new BeanPropertyRowMapper<Category>(Category.class),cid);
    }

    /**
     * 根据类别id删除类别信息
     * @param cid 类别id
     */
    @Override
    public void del(int cid) {
        String sql="DELETE FROM tab_category WHERE cid=?";
        //执行
        String sql1="ALTER TABLE tab_category AUTO_INCREMENT = 1";
        template.update(sql,cid);
        template.update(sql1);
    }

    /**
     * 通过类别名称查找类别信息
     * @param cname 类别名称
     * @return
     */
    @Override
    public Category findByUserName(String cname) {

        Category category=null;
        //定义sql
        try {
            String sql = "select * from tab_category where cname=?";
            //执行
            category = template.queryForObject(sql, new BeanPropertyRowMapper<Category>(Category.class),cname);
        }catch (DataAccessException e){

        }
        return category;
    }

    /**
     * 增加分类信息
     * @param cname 类别名称
     */
    @Override
    public void add(String cname) {
        String sql="insert into tab_category(cname) values(?)";
        //执行
        template.update(sql,cname);

    }

    /**
     * 根据id查询类别信息
     * @param cid 类别id
     * @return
     */
    @Override
    public Category findOne(int cid) {
        String sql="select * from tab_category where cid=?";
        return template.queryForObject(sql,new BeanPropertyRowMapper<Category>(Category.class),cid);
    }

    /**
     * 更改分类
     * @param cname 类别名称
     * @param cid 类别id
     */
    @Override
    public void update(String cname, int cid) {
        String sql ="update tab_category set cname=? where cid=?";

        template.update(sql,cname,cid);

    }



}
