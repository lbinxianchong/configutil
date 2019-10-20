package com.lbin.util.crawler.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class SearchPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    private String url;

    /**
     * 漫画标题
     */
    private String title;

    /**
     * 漫画封面图片url
     */
    private String urlimg;

    /**
     * 漫画最后章节
     */
    private String chapterlast;
}
