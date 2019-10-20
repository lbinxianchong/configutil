package com.lbin.util.crawler.website.mh160_1;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

//搜索url实体
@Data
@Gecco(matchUrl = "https://www.mh160_1.co/statics/search.aspx?key={key}", pipelines = "searchConsolePipeline")
public class Mh160Search implements Serializable, HtmlBean {
    private static final long serialVersionUID = 1L;


    @Request
    private HttpRequest request;

    @RequestParameter("key")
    private String key;

    /**
     * 漫画搜索实体
     */
    @Text
    @HtmlField(cssPath = ".mh-search-result > ul > li")
    private List<Mh160SearchPojo> searchPojo;



}
