package com.lbin.util.crawler.website.mh160_1;

import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.spider.HtmlBean;
import lombok.Data;

import java.io.Serializable;

//漫画章节对象实体
@Data
public class Mh160Chapter implements Serializable, HtmlBean {
    /**
     * 漫画章节url
     */
    @Href(click = false)
    @HtmlField(cssPath = " a")
    private String chapterurl;

    @Text
    @HtmlField(cssPath = " a > p")
    private String chaptername;

}
