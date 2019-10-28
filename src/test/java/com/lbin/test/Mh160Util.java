package com.lbin.test;

import com.lbin.util.crawler.api.CrawlerApi;
import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.config.PageParserConfig;
import com.lbin.util.crawler.core.XxlCrawlerG;
import com.lbin.util.crawler.core.select.FieldSelect;
import com.lbin.util.crawler.core.select.SelectPojo;
import com.lbin.util.crawler.model.Chapter;
import com.lbin.util.crawler.model.ChapterImg;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.pageparser.*;
import com.lbin.util.crawler.util.*;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ChapterImgPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ChapterPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ComicPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160SearchPageParser;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.loader.strategy.JsoupPageLoader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Mh160Util {


    private static CrawlerApi crawlerApi = new CrawlerApi("Demo");

    /**
     * demo 搜索
     */
    @Test
    public void Mh160Search() {
        String search = "超人";
        int page = 1;
        List<String> list = new ArrayList<>();
        list.add(search);
        list.add(Integer.toString(page));

        SelectPojo selectPojo = new SelectPojo();
        selectPojo.setPageSelect(".mh-search-result > ul > li");
        selectPojo.setLevel(2);

        Map<String, FieldSelect> map=new HashMap<>();
        FieldSelect urls = new FieldSelect();
        urls.setCssQuery(".mh-works-info > div > h4 > a");
        urls.setSelectType("ATTR");
        urls.setSelectVal("abs:href");
        FieldSelect names = new FieldSelect();
        names.setCssQuery(".mh-works-info > div > h4 > a");
        FieldSelect urlimgs = new FieldSelect();
        urlimgs.setCssQuery(".mh-worksbox > div > div > a > img");
        urlimgs.setSelectType("ATTR");
        urlimgs.setSelectVal("abs:src");
        FieldSelect chapterlasts = new FieldSelect();
        chapterlasts.setCssQuery(".mh-worksbox > div > p > a > span");

        map.put("url",urls);
        map.put("name",names);
        map.put("urlimg",urlimgs);
        map.put("chapterlast",chapterlasts);
        selectPojo.setFieldSelectList(map);

        String url=crawlerApi.getPageParserConfig().getSearchPath();
        SearchPageParsers searchPageParser = new SearchPageParsers();
        for (String s : list) {
            url = url.replaceFirst("%pram%", s);
        }
        // 构造爬虫
        XxlCrawlerG crawler = new XxlCrawlerG.Builder()
                .setUrls(url)
                .setAllowSpread(false)
                .setThreadCount(1)
                .setSelectPojo(selectPojo)
                .setPageLoader(new JsoupPageLoader())
                .setPageParser(searchPageParser)
                .build();
        // 启动
        crawler.start(true);
        List<SearchPojo> searchPojos = searchPageParser.getSearchPojoList();
        for (SearchPojo searchPojo : searchPojos) {
            System.out.println(searchPojo);
        }
    }

    @Test
    public void Mh160Test1() {
        String url = "https://www.mh160.co/kanmanhua/10109/";
        Comic comic = crawlerApi.Mh160Comic(url);
        List<String> chapterUrl = comic.getChapterUrl();
        String s = chapterUrl.get(chapterUrl.size()-1);
        ChapterImg chapterImg = crawlerApi.Mh160ChapterImg(s);
        ChapterImg jpg = CrawlerUtil.DownloadChapterImg(chapterImg, "jpg");
        System.out.println(jpg);
    }


}
