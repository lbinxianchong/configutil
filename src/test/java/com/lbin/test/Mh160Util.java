package com.lbin.test;

import com.lbin.util.crawler.api.CrawlerApi;
import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.config.PageParserConfig;
import com.lbin.util.crawler.core.XxlCrawlerG;
import com.lbin.util.crawler.model.Chapter;
import com.lbin.util.crawler.model.ChapterImg;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.pageparser.ChapterImgPageParser;
import com.lbin.util.crawler.pageparser.ChapterPageParser;
import com.lbin.util.crawler.pageparser.ComicPageParser;
import com.lbin.util.crawler.pageparser.SearchPageParser;
import com.lbin.util.crawler.util.*;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ChapterImgPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ChapterPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ComicPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160SearchPageParser;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.loader.strategy.JsoupPageLoader;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class Mh160Util {


    private static CrawlerApi crawlerApi = new CrawlerApi("Mh160");

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



        String url=crawlerApi.getPageParserConfig().getSearchPath();
        SearchPageParser searchPageParser = crawlerApi.getPageParserConfig().getSearchPageParser();
        for (String s : list) {
            url = url.replaceFirst("%pram%", s);
        }
        // 构造爬虫
        XxlCrawlerG crawler = new XxlCrawlerG.Builder()
                .setUrls(url)
                .setAllowSpread(false)
                .setThreadCount(1)
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
