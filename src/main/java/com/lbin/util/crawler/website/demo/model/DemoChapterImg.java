package com.lbin.util.crawler.website.demo.model;

import com.lbin.util.crawler.model.ChapterImg;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;

//章节url实体
@Data
@EqualsAndHashCode(callSuper = true)
public class DemoChapterImg extends ChapterImg implements Serializable {
    private static final long serialVersionUID = 1L;

    @PageFieldSelect(cssQuery = "")
    private String comicname;

    @PageFieldSelect(cssQuery = "")
    private String chaptername;

    @PageFieldSelect(selectType =ATTR,selectVal = "src",cssQuery = "")
    private String img;

    @PageFieldSelect(cssQuery="")
    private int number;
}