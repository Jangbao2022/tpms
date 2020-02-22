package com.itcast.tpms.dto;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 封装页面类
 * blogs 大神(复数) 等等
 *
 * @param <E>
 */
@Data
@Component
public class PageDto<E> {

    //elements列表
    private List<E> elements;
    //总页数
    private Integer totalPage;
    //当前页
    private Integer page;
    //前一页
    private Integer afterPage;
    //后一页
    private Integer prePage;

    //静态pageUrl
    private String pageUrl;

    public PageDto() {

    }

    public PageDto(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    /**
     * 设置totalPage数量
     *
     * @param total 有多少个elements
     */
    private void countTotalPage(Integer total, Integer limit) {
        if (total % limit == 0) {
            totalPage = total / limit;
        } else {
            totalPage = total / limit + 1;
        }
    }

    /**
     * 计算当前页,前页和后页
     *
     * @param page
     */
    private void countPreAndAfter(Integer page) {

        //如果总共只有一页
        if (totalPage <= 1) {
            this.page = 1;
            prePage = this.page;
            afterPage = this.page;
            return;
        }

        if (page <= 1) {
            //如果当前页小于等于第一页
            this.page = 1;
            prePage = this.page;
            afterPage = this.page + 1;

        } else if (page >= totalPage) {
            //如果当前页大于等于最后一页
            this.page = totalPage;
            prePage = this.page - 1;
            afterPage = this.page;
        } else {
            //当前页在中间
            this.page = page;
            prePage = this.page - 1;
            afterPage = this.page + 1;
        }
    }

    public void init(SearchDto searchDto) {
        countTotalPage(searchDto.getTotal(), searchDto.getLimit());
        countPreAndAfter(searchDto.getPage() == null ? 1 : searchDto.getPage());
        pageUrl = searchDto.getKeyword() == null ? pageUrl + "?keyword=" + searchDto.getKeyword() : pageUrl;
    }


}
