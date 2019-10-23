package com.lbin.util.crawler.website.mh160.model;

import com.lbin.util.crawler.model.Chapter;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;


@Data
@EqualsAndHashCode(callSuper = true)
@PageSelect(cssQuery = "#mh-chapter-list-ol-0 > li")
public class Mh160Chapter extends Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 章节url
     */
    @PageFieldSelect(cssQuery = "a",selectType = ATTR,selectVal = "abs:href")
    private String chapterurl;

    /**
     * 章节名字
     */
    @PageFieldSelect(cssQuery = "a")
    private String chaptername;


}