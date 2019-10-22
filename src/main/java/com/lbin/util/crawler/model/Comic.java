package com.lbin.util.crawler.model;


import com.lbin.util.crawler.util.ModelUtil;
import lombok.Data;

import java.util.List;

@Data
public class Comic {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    private String comicurl;

    /**
     * 漫画标题
     */
    private String comicname;

    /**
     * 漫画封面图片url
     */
    private String coverimgurl;

    /**
     * 漫画作者
     */
    private List<String> author;

    /**
     * 漫画类别
     */
    private List<String> category;

    /**
     * 漫画介绍
     */
    private String introduction;
/*
    *//**
     * 漫画章节实体
     *//*
    private List<Chapter> chapter;

    *//**
     * 漫画章节url
     *//*
    private List<String> chapterurl;

    *//**
     * 漫画章节名字
     *//*
    private List<String> chaptername;


    public Comic getComic(){

        Comic comic =new Comic();
        comic.setComicurl(ModelUtil.ToString(getComicurl()));
        comic.setComicname(ModelUtil.ToString(getComicname()));
        comic.setCoverimgurl(ModelUtil.ToString(getCoverimgurl()));
        comic.setAuthor(ModelUtil.ToListString(getAuthor()));
        comic.setCategory(ModelUtil.ToListString(getCategory()));
        comic.setIntroduction(ModelUtil.ToString(getIntroduction()));
//        comic.setChapter(getChapter());
        comic.setChapterurl(ModelUtil.ToListString(getChapterurl()));
        comic.setChaptername(ModelUtil.ToListString(getChaptername()));

        return comic;
    }*/


}
