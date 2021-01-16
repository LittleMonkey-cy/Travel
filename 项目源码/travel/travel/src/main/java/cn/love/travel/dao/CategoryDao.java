package cn.love.travel.dao;

import cn.love.travel.pojo.Category;

import java.util.List;

/**
* @author 陈钰
* @Description
* @Date 8:53 2020/12/20
* @Param
* @return
**/
public interface CategoryDao {
    /**
     * 显示所有类别信息
     * @return
     */
     List<Category> findAll();

    /**
     * 通过类别编号查询类别信息
     * @param cid 类别id
     * @return
     */
     Category findById(int cid);

    /**
     * 根据类别id删除类别信息
     * @param cid 类别id
     */
    void del(int cid);

    /**
     * 根据类别名进行查重验证
     * @param cname 类别名称
     * @return
     */
    Category findByUserName(String cname);

    /**
     * 添加类别
     * @param cname 类别名称
     */
    void add(String cname);

    /**
     * 根据类别id查找类别信息
     * @param cid 类别id
     * @return
     */
    Category findOne(int cid);

    /**
     * 类别修改
     * @param cname 类别名称
     * @param cid 类别id
     */
    void update(String cname ,int cid);
}
