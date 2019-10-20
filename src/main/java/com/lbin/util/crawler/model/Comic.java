package com.lbin.util.crawler.model;


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
    private String comictitle;

    /**
     * 漫画封面图片url
     */
    private String coverurlimg;

    /**
     * 漫画封面本地图片
     */

    private String coverimg;

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

    /**
     * 漫画章节实体
     */
    private List<Chapter> chapter;

    /**
     * 漫画章节url
     */
    private List<String> chapterurl;

    /**
     * 漫画章节名字
     */
    private List<String> chaptername;

    /*public ComicPojo getComicPojo(){
        ComicPojo comicPojo = new ComicPojo();
        comicPojo.setTitle(getComictitle());
        comicPojo.setAuthor(getAuthor().toString());
        comicPojo.setCategory(getCategory().toString());
        comicPojo.setIntroduction(getIntroduction());

        return comicPojo;
    }*/


}
