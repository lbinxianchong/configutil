package com.lbin.util.crawler.website.mh160.model;

import com.lbin.util.crawler.model.ChapterImg;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;

//章节url实体
@Data
@EqualsAndHashCode(callSuper = true)
public class Mh160ChapterImg extends ChapterImg implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画名字
     */
    @PageFieldSelect(cssQuery = ".w996.title.pr > h1 > a")
    private String comicname;

    /**
     * 章节名字
     */
    @PageFieldSelect(cssQuery = ".w996.title.pr > h2")
    private String chaptername;

    /**
     * 漫画url
     */
    @PageFieldSelect(cssQuery = "#qTcms_pic",selectType =ATTR,selectVal = "abs:src")
    private String imgurl;

    /**
     * 漫画url
     */
    @PageFieldSelect(cssQuery = "",selectType =ATTR,selectVal = "abs:src")
    private List<String> imgurls;

    /**
     * 漫画数量
     */
    @PageFieldSelect(cssQuery="#k_total")
    private int number;
}