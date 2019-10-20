package com.lbin.util.crawler.website.mh160_2.mh160;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

//搜索url实体
@Data
public class Mh160Search implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画搜索实体
     */

    @PageFieldSelect(cssQuery = ".mh-search-result > ul > li")
    private List<Mh160SearchPojo> searchPojo;

}
