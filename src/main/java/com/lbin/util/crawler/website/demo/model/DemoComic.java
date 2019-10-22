package com.lbin.util.crawler.website.demo.model;

import com.lbin.util.crawler.model.Comic;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

//漫画url实体
@Data
@EqualsAndHashCode(callSuper = true)
public class DemoComic extends Comic implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画标题
     */
    @PageFieldSelect(cssQuery = "")
    private String comicname;

    /**
     * 漫画封面图片url
     */
    @PageFieldSelect(cssQuery = "")
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
    @PageFieldSelect(cssQuery = "")
    private String introduction;

    /**
     * 漫画章节url
     */
    @PageFieldSelect(cssQuery = "")
    private List<String> chapterurl;

}
