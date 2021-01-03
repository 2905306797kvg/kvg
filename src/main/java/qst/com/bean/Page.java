package qst.com.bean;

import java.util.List;

public class Page<T> {
    private Integer pageSize;//每页总行数
    private Integer currentPage;//当前页数
    private Integer totalRows;//总行数
    private Integer totalPages;//总页数
    private Integer beforePage;//上一页
    private Integer afterPage;//下一页

    private List<T> list;

    public Page() {}

    public Page(Integer pageSize, Integer currentPage, Integer totalRows, List<T> list) {
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalRows = totalRows;
        this.list = list;
        //4、一共多少页totalPages
        this.totalPages=this.totalRows%this.pageSize==0?this.totalRows/this.pageSize:this.totalRows/this.pageSize+1;
        //6、上一页
        this.beforePage=this.currentPage-1<=0?1:this.currentPage-1;
        //7、下一页
        this.afterPage=this.currentPage+1>=this.totalPages?this.totalPages:this.currentPage+1;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        this.totalRows = totalRows;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getBeforePage() {
        return beforePage;
    }

    public void setBeforePage(Integer beforePage) {
        this.beforePage = beforePage;
    }

    public Integer getAfterPage() {
        return afterPage;
    }

    public void setAfterPage(Integer afterPage) {
        this.afterPage = afterPage;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
