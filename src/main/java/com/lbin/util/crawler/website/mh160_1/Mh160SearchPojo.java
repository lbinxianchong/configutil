package com.lbin.util.crawler.website.mh160_1;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;

import com.lbin.util.crawler.model.SearchPojo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

//搜索url对象实体
@Data
@EqualsAndHashCode(callSuper = true)
public class Mh160SearchPojo extends SearchPojo implements Serializable, HtmlBean {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    @Href
    @HtmlField(cssPath = ".mh-works-info > div > h4 > a")
    private String url;

    /**
     * 漫画标题
     */
    @Text
    @HtmlField(cssPath = ".mh-works-info > div > h4 > a")
    private String title;

    /**
     * 漫画封面图片url
     */
    @Image
    @HtmlField(cssPath = ".mh-worksbox > div > div > a > img")
    private String urlimg;

    /**
     * 漫画最后章节
     */
    @Text
    @HtmlField(cssPath = ".mh-worksbox > div > p > a > span")
    private String chapterlast;
}
