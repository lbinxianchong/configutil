package com.lbin.util.crawler.website.mh160.model;

import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import com.lbin.util.ConfigProperties;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.util.ModelUtil;
import com.xuxueli.crawler.annotation.PageFieldSelect;
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
    @PageFieldSelect(cssQuery = ".mh-date-info-name > h4 > a")
    private String comicname;

    /**
     * 漫画封面图片url
     */
    @PageFieldSelect(cssQuery = ".mh-date-bgpic > a > img")
    private String coverurlimg;

    /**
     * 漫画封面本地图片
     */
    @PageFieldSelect(cssQuery = ".mh-date-bgpic > a > img")
    private String coverimg;

    /**
     * 漫画作者
     */
    @PageFieldSelect(cssQuery = "")
    private List<String> author;

    /**
     * 漫画类别
     */
    @PageFieldSelect(cssQuery = "")
    private List<String> category;

    /**
     * 漫画介绍
     */
    @PageFieldSelect(cssQuery = "#workint > p")
    private String introduction;

    /**
     * 漫画章节url
     */
    @PageFieldSelect(cssQuery = "#mh-chapter-list-ol-0 > li > a ")
    private List<String> chapterurl;

    public Comic getComic(){

        Comic comic =new Comic();
        comic.setComicurl(ModelUtil.ToString());
        comic.setComicname(ModelUtil.ToString(getComicname()));
        comic.setCoverurlimg(ModelUtil.ToString(getCoverurlimg()));
        comic.setCoverimg(ModelUtil.ToString(getCoverimg()));
        comic.setAuthor(ModelUtil.ToListString(getAuthor()));
        comic.setCategory(ModelUtil.ToListString(getCategory()));
        comic.setIntroduction(ModelUtil.ToString(getIntroduction()));
        comic.setChapter(null);
        comic.setChapterurl(ModelUtil.ToListString(getChapterurl()));
        comic.setChaptername(ModelUtil.ToListString(null));

        return comic;
    }
}
