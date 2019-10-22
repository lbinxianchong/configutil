package com.lbin.util.crawler.website.mh160.model;

import com.lbin.util.crawler.model.ChapterImg;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;

//章节url实体
@Data
@EqualsAndHashCode(callSuper = true)
public class Mh160ChapterImg extends ChapterImg implements Serializable {
    private static final long serialVersionUID = 1L;

    @PageFieldSelect(cssQuery = ".w996.title.pr > h1 > a")
    private String comicname;

    @PageFieldSelect(cssQuery = ".w996.title.pr > h2")
    private String chaptername;

    @PageFieldSelect(selectType =ATTR,selectVal = "src",cssQuery = "#qTcms_pic")
    private String img;

    @PageFieldSelect(cssQuery="#k_total")
    private int number;
}