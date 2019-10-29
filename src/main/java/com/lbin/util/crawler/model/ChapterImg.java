package com.lbin.util.crawler.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class ChapterImg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 漫画章节url
     */
    private String baseUrl;

    /**
     * 漫画名字
     */
    private String comicname;

    /**
     * 章节名字
     */
    private String chaptername;

    /**
     * 漫画url
     */
    private String imgurl;

    /**
     * 漫画url
     */
    private List<String> imgurls;

    /**
     * 漫画本地地址
     */
    private List<String> imglocals;

    /**
     * 漫画数量
     */
    private int number;
}