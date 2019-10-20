package com.lbin.util.crawler.website.mh160;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.lbin.util.ConfigProperties;
import com.lbin.util.crawler.model.Comic;
import lombok.Data;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

//漫画url实体
@Data
public class Mh160Comic implements Serializable {
    private static final long serialVersionUID = 1L;



    /**
     * 漫画标题
     */
    @Text
    @HtmlField(cssPath = ".mh-date-info-name > h4 > a")
    private String title;

    /**
     * 漫画封面图片url
     */
    @Image
    @HtmlField(cssPath = ".mh-date-bgpic > a > img")
    private String urlimg;

    /**
     * 漫画封面本地图片
     */
    @Image(download = "")
    @HtmlField(cssPath = ".mh-date-bgpic > a > img")
    private String coverimg;

    /**
     * 漫画作者
     */
    @Text
    @HtmlField(cssPath = "")
    private List<String> author;

    /**
     * 漫画类别
     */
    @Text
    @HtmlField(cssPath = "")
    private List<String> category;

    /**
     * 漫画介绍
     */
    @Text
    @HtmlField(cssPath = "#workint > p")
    private String introduction;


    /**
     * 漫画章节url
     */
    @Href(click = false)
    @HtmlField(cssPath = "#mh-chapter-list-ol-0 > li > a ")
    private List<String> chapterurl;

    /*@Text
    @HtmlField(cssPath = "#mh-chapter-list-ol-0 > li ")
    private List<Mh160Chapter> chapter;*/


    /*public Comic getComic(){
        Comic comic = new Comic();
        comic.setComicproject(getProject());
        comic.setComicurl(getRequest().getUrl());
        comic.setComictitle(getTitle());
        comic.setCoverurlimg(getUrlimg());
        comic.setCoverimg(getCoverimg());
        comic.setAuthor(getAuthor());
        comic.setCategory(getCategory());
        comic.setIntroduction(getIntroduction());
        comic.setChapter(null);
        comic.setChapterurl(getChapterurl());
        return comic;
    }*/
}
