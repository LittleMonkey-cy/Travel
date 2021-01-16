package cn.love.travel.service.impl;

import cn.love.travel.dao.CategoryDao;
import cn.love.travel.dao.impl.CategoryDaoImpl;
import cn.love.travel.pojo.Category;
import cn.love.travel.service.CategoryService;

import java.util.List;

/**
* @author 陈钰
* @Description 业务逻辑层实现类
* @Date  2020/12/20
* @Param
* @return
**/
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CategoryDaoImpl();

    /**
     * 查找所有
     * @return
     */
    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }

    /**
     *删除单个类别信息
     * @param cid
     */
    @Override
    public void del(String cid) {
        categoryDao.del(Integer.parseInt(cid));
    }

    /**
     *添加类别
     * @param cname 类别名称
     * @return
     */
    @Override
    public Boolean add(String cname) {
        Category c = categoryDao.findByUserName(cname);
        if (c!=null){
            // 已存在
            return false;
        }else {
            // 保存类别
            categoryDao.add(cname);
            return true;
        }
    }

    /**
     *根据类别id查找类别信息
     * @param cid 类别id
     * @return
     */
    @Override
    public Category findOne(String cid) {
        return categoryDao.findOne(Integer.parseInt(cid));
    }

    /**
     *修改类别
     * @param cname
     * @param cid
     */
    @Override
    public void update(String cname, String cid) {
        categoryDao.update(cname,Integer.parseInt(cid));

    }

    /**
     *批量删除
     * @param cids
     */
    @Override
    public void delall(String[] cids) {
        // 循环遍历选中的id值
        for (String cid:cids){
            categoryDao.del(Integer.parseInt(cid));
        }

    }

    /**
     *
     * @param cname
     * @return
     */
    @Override
    public Category findName(String cname) {
        return categoryDao.findByUserName(cname);
    }
}

