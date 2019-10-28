package com.lbin.util.crawler.website.demo.model;

import com.lbin.util.crawler.model.Chapter;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import lombok.*;

import java.io.Serializable;

import static com.xuxueli.crawler.conf.XxlCrawlerConf.SelectType.ATTR;


@Getter
@Setter
@RequiredArgsConstructor
@EqualsAndHashCode(callSuper = true)
@PageSelect(cssQuery = "")
public class DemoChapter extends Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 章节url
     */
    @PageFieldSelect(cssQuery = "",selectType = ATTR,selectVal = "abs:href")
    private String chapterurl;

    /**
     * 章节名字
     */
    @PageFieldSelect(cssQuery = "")
    private String chaptername;


}