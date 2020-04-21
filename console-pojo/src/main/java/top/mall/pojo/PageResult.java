package top.mall.pojo;

import com.github.pagehelper.PageInfo;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {
    int pages;
    int total;
    int currentPage;
    int pageSize;
    List<T> list;

    public PageResult() {
    }

    public PageResult(int pages, int total, int currentPage, int pageSize, List<T> list) {
        this.pages = pages;
        this.total = total;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
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

    public static<T> PageResult<T> success(List<T> list) {
        PageInfo<T> info = new PageInfo<T>(list);
        return new PageResult<T>(
                info.getPages(),
                (int) info.getTotal(),
                info.getPageNum(),
                info.getPageSize(),
                list);
    }
}
