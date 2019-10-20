package com.lbin.util.crawler.model;

import lombok.Data;

import java.io.Serializable;


@Data
public class ChapterImg implements Serializable {
    private static final long serialVersionUID = 1L;


    private String chapterimgurl;
    //漫画名字
    private String comicname;

    //章节名字
    private String chaptername;

    private String img;

    private int number;
}