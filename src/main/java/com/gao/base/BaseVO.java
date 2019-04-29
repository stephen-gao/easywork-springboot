package com.gao.base;

import java.util.List;

/**
 * @Author gs
 * @Date created time 2019/4/29 18:04
 * @Description
 */
public class BaseVO {
    //默认num
    private Long DEFAULT_PAGE_NUM = 1L;
    //默认size
    private Long DEFAULT_PAGE_SIZE = 10L;

    private String DEFAULT_SORT_TYPE = "ASC";

    private Long current;

    private Long size;

    private List<String> sortList;

    private String sortType;

    public Long getCurrent() {
        return null == this.current ? this.DEFAULT_PAGE_NUM : this.current;
    }

    public void setCurrent(Long current) {
        this.current = current;
    }

    public Long getSize() {
        return null == this.size ? this.DEFAULT_PAGE_SIZE : this.size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<String> getSortList() {
        return sortList;
    }

    public void setSortList(List<String> sortList) {
        this.sortList = sortList;
    }

    public String getSortType() {
        return sortType;
    }

    public void setSortType(String sortType) {
        this.sortType = sortType;
    }
}
