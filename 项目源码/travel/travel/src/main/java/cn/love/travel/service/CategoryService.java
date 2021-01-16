package cn.love.travel.service;

import cn.love.travel.pojo.Category;

import java.util.List;

/**
* @author 陈钰
* @Description 业务逻辑层
* @Date 2020/12/20
* @Param
* @return
**/
public interface CategoryService {

    /**
     * 查询所有类别
     * @return
     */
     List<Category> findAll();

    /**
     * 删除单个类别信息
     * @param cid
     */
    void del(String cid);

    /**
     *添加类别
     * @param cname
     * @return
     */
    Boolean add(String cname);

    /**
     * 根据类别id查找类别信息
     * @param cid 类别id
     * @return
     */
    Category findOne(String cid);

    /**
     * 修改类别
     * @param cname
     * @param cid
     */
    void update(String cname, String cid);

    /**
     * 批量删除
     * @param cids
     */
    void delall(String[] cids);

    /**
     * 查找名称
      * @param cname
     * @return
     */
    Category findName(String cname);
}
