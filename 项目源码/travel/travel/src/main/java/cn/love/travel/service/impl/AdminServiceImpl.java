package cn.love.travel.service.impl;

import cn.love.travel.dao.SellerDao;
import cn.love.travel.dao.impl.SellerDaoImpl;
import cn.love.travel.pojo.PageBean;
import cn.love.travel.pojo.Seller;
import cn.love.travel.service.AdminService;

import java.util.List;
/**
* @author 陈钰
* @Description 业务逻辑层的实现类
* @Date 2020/12/24
* @Param
* @return
**/
public class AdminServiceImpl implements AdminService {
    // 实例化对象
    private SellerDao adminDao=new SellerDaoImpl();
    @Override
    public Seller adminlogin(Seller seller) {

        return adminDao.login(seller.getUsername(),seller.getPassword());
    }

    /**
     * 管理员的显示
     * @return
     */
    @Override
    public List<Seller> listadmin() {
        return adminDao.listadmin();

    }

    /**
     * 管理员的添加
     * @param seller
     * @return
     */
    @Override
    public Boolean addadmin(Seller seller) {
        // 判断用户名是否存在
        Seller s = adminDao.findByUserName(seller.getUsername());
        if (s!=null){
            // 已存在
            return false;
        }else {
            // 保存管理员信息
            adminDao.addadmin(seller);
            return true;
        }


    }

    /**
     * 删除管理员
     * @param sid
     */
    @Override
    public void deladmin(String sid) {
        adminDao.del(Integer.parseInt(sid));
    }

    /**
     * 管理员更改查询功能
     * @param sid
     * @return
     */
    @Override
    public Seller findOneadmin(String sid) {
        return adminDao.findOneadmin(Integer.parseInt(sid));
    }

    /**
     * 更改管理员信息
     * @param seller
     */
    @Override
    public void update(Seller seller) {
        adminDao.update(seller);
    }

    /**
     * 批量删除
     * @param sids
     */
    @Override
    public void delall(String[] sids) {
        //sid遍历
        for(String sid:sids){
            //调用dao删除
            adminDao.del(Integer.parseInt(sid));
        }

    }

    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @param name
     * @return
     */
    @Override
    public PageBean<Seller> pageQuery(int currentPage, int pageSize,String name) {
        // 实例化对象
        PageBean<Seller> pb=new PageBean<Seller>();

        // 设置当前页码
        pb.setCurrentPage(currentPage);
        //设置每页显示条数
        pb.setPageSize(pageSize);
        // 总记录数
        int totalCount=adminDao.findTotalCount(name);
        pb.setTotalCount(totalCount);

        // 设置当前页集合
        // 开始记录
        int start=(currentPage-1)*pageSize;
        List<Seller> list=adminDao.findByPage(start,pageSize,name);
        pb.setList(list);

        // 设置总记录数
        int totalPage = totalCount % pageSize == 0 ? totalCount / pageSize :(totalCount / pageSize) + 1 ;
        pb.setTotalPage(totalPage);
        return pb;
    }

    /**
     * 修改用户员管理信息
     * @param seller
     * @param sid
     */
    @Override
    public void Personupdate(Seller seller, int sid) {
        adminDao.personupdate(seller,sid);
    }

    /**
     * 管理员中心展示
     * @param sid
     * @return
     */
    @Override
    public Seller personfind(int sid) {
        return adminDao.findOneadmin(sid);
    }


}
