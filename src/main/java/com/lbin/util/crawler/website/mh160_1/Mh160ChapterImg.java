package com.lbin.util.crawler.website.mh160_1;

import com.xuxueli.crawler.annotation.PageFieldSelect;
import lombok.Data;

import java.io.Serializable;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;

//章节url实体
@Data
public class Mh160ChapterImg implements Serializable {
    private static final long serialVersionUID = 1L;



    //漫画名字
    @PageFieldSelect(cssQuery = ".w996.title.pr > h1 > a")
    private String comicname;

    //章节名字
    @PageFieldSelect(cssQuery = ".w996.title.pr > h2")
    private String chaptername;

    @PageFieldSelect(selectType =ATTR,selectVal = "src",cssQuery = "#qTcms_pic")
    private String img;

    @PageFieldSelect(cssQuery="#k_total")
    private int number;
}