package com.lbin.util.crawler.website.mh160.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;


@Data
public class Mh160Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 章节url
     */
    private String chapterurl;

    /**
     * 漫画名字
     */
    private String comicname;

    /**
     * 章节名字
     */
    private String chaptername;

    /**
     * 章节图片数量
     */
    private Integer number;

}