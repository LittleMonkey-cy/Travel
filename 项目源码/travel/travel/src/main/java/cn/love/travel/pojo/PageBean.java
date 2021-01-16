package cn.love.travel.pojo;

import java.util.List;

/**
* @author 陈钰
* @Description 分页对象（可供后续共用）
* @Date 7:49 2020/12/20
* @Param
* @return
**/
public class PageBean<T> {
    /**
     * 总数
     */
    private  int totalCount;
    /**
     * 总页数
     */
    private  int totalPage;
    /**
     * 当前页数
     */
    private  int currentPage;
    /**
     * 一页的个数
     */
    private  int pageSize;
    /**
     * 每页显示的数据集合
     */
    private List<T> list ;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

}
