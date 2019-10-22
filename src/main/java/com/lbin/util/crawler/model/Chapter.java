package com.lbin.util.crawler.model;

import lombok.Data;


import java.io.Serializable;
import java.util.List;


@Data
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画名字
     */
    private String comicname;

    /**
     * 章节名字
     */
    private String chaptername;

    /**
     * 章节url
     */
    private String chapterurl;


}