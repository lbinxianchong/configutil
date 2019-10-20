package com.lbin.util.crawler.model;

import lombok.Data;


import java.io.Serializable;
import java.util.List;


@Data
public class Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 章节url
     */
    private String chapterurl;

    /**
     * 漫画名字
     */
    private String comictitle;

    /**
     * 章节名字
     */
    private String chaptername;

    /**
     * 图片地址
     */
    private String imgurl;

    /**
     * 图片地址List
     */
    private List<String> imgurlList;

    /**
     * 章节图片数量
     */
    private Integer number;

    /**
     * 章节排序
     */
    private Integer indexno;

    /**
     * 漫画id
     */
    private Long pojpid;


}