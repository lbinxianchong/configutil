package com.lbin.util.crawler.website.demo.model;

import com.lbin.util.crawler.model.SearchPojo;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import lombok.*;

import java.io.Serializable;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;


//搜索url对象实体
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PageSelect(cssQuery = ".mh-search-result > ul > li")
public class DemoSearch extends SearchPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    @PageFieldSelect(cssQuery = ".mh-works-info > div > h4 > a",selectType = ATTR,selectVal = "abs:href")
    private String url;

    /**
     * 漫画标题
     */
    @PageFieldSelect(cssQuery = ".mh-works-info > div > h4 > a")
    private String name;

    /**
     * 漫画封面图片url
     */
    @PageFieldSelect(cssQuery = ".mh-worksbox > div > div > a > img",selectType = ATTR,selectVal = "abs:src")
    private String urlimg;

    /**
     * 漫画最后章节
     */
    @PageFieldSelect(cssQuery = ".mh-worksbox > div > p > a > span")
    private String chapterlast;
}
