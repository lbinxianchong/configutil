package com.lbin.util.crawler.website.mh160;

import com.lbin.util.crawler.model.SearchPojo;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

//搜索url对象实体
@Data
@EqualsAndHashCode(callSuper = true)
@PageSelect(cssQuery = ".mh-search-result > ul > li")
public class Mh160Search extends SearchPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    @PageFieldSelect(cssQuery = ".mh-works-info > div > h4 > a")
    private String url;

    /**
     * 漫画标题
     */
    @PageFieldSelect(cssQuery = ".mh-works-info > div > h4 > a")
    private String title;

    /**
     * 漫画封面图片url
     */
    @PageFieldSelect(cssQuery = ".mh-worksbox > div > div > a > img")
    private String urlimg;

    /**
     * 漫画最后章节
     */
    @PageFieldSelect(cssQuery = ".mh-worksbox > div > p > a > span")
    private String chapterlast;
}
