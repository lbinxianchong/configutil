package com.lbin.util.crawler.model;

import lombok.Data;


import java.io.Serializable;


@Data
public class Chapter implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 章节url
     */
    private String chapterurl;

    /**
     * 章节名字
     */
    private String chaptername;


}