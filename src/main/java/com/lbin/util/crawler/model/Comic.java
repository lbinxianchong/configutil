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

    public Comic getComic(){
        /*Comic comic =new Comic();
        comic.setComicurl(getComicurl());
        comic.setComicname(getComicname());
        comic.setCoverurlimg(getCoverurlimg());
        comic.setCoverimg(getCoverimg());
        comic.setAuthor(getAuthor());
        comic.setCategory(getCategory());
        comic.setIntroduction(getIntroduction());
        comic.setChapter(getChapter());
        comic.setChapterurl(getChapterurl());
        comic.setChaptername(getChaptername());*/


        Comic comic =new Comic();
        comic.setComicurl(ModelUtil.ToString(getComicurl()));
        comic.setComicname(ModelUtil.ToString(getComicname()));
        comic.setCoverurlimg(ModelUtil.ToString(getCoverurlimg()));
        comic.setCoverimg(ModelUtil.ToString(getCoverimg()));
        comic.setAuthor(ModelUtil.ToListString(getAuthor()));
        comic.setCategory(ModelUtil.ToListString(getCategory()));
        comic.setIntroduction(ModelUtil.ToString(getIntroduction()));
        comic.setChapter(getChapter());
        comic.setChapterurl(ModelUtil.ToListString(getChapterurl()));
        comic.setChaptername(ModelUtil.ToListString(getChaptername()));

        return comic;
    }


}