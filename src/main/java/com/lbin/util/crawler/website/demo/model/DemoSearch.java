package com.lbin.util.crawler.website.demo.model;

import com.lbin.util.crawler.model.SearchPojo;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;


@Data
@EqualsAndHashCode(callSuper = true)
@PageSelect(cssQuery = "")
public class DemoSearch extends SearchPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    @PageFieldSelect(cssQuery = "",selectType =ATTR,selectVal = "href")
    private String url;

    /**
     * 漫画标题
     */
    @PageFieldSelect(cssQuery = "")
    private String name;

    /**
     * 漫画封面图片url
     */
    @PageFieldSelect(cssQuery = "",selectType =ATTR,selectVal = "src")
    private String urlimg;

    /**
     * 漫画最后章节
     */
    @PageFieldSelect(cssQuery = "")
    private String chapterlast;
}
