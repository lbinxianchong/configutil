package com.lbin.util.crawler.website.demo.model;

import com.lbin.util.crawler.model.Comic;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import lombok.*;

import java.io.Serializable;
import java.util.List;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;

//漫画url实体
@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DemoComic extends Comic implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画标题
     */
    @PageFieldSelect(cssQuery = ".mh-date-info-name > h4 > a")
    private String comicname;

    /**
     * 漫画封面图片url
     */
    @PageFieldSelect(cssQuery = ".mh-date-bgpic > a > img",selectType = ATTR,selectVal = "abs:src")
    private String coverimgurl;

    /**
     * 漫画作者
     */
    @PageFieldSelect(cssQuery = "")
    private List<String> author;

    /**
     * 漫画类别
     */
    @PageFieldSelect(cssQuery = "")
    private List<String> category;

    /**
     * 漫画介绍
     */
    @PageFieldSelect(cssQuery = "#workint > p")
    private String introduction;


}
