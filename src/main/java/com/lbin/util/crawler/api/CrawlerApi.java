package com.lbin.util.crawler.api;

import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.core.pageparser.MapPageParser;
import com.lbin.util.crawler.core.select.Select;
import com.lbin.util.crawler.model.ChapterImg;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.model.Search;
import com.lbin.util.crawler.pageparser.ChapterImgPageParser;
import com.lbin.util.crawler.pageparser.ChapterPageParser;
import com.lbin.util.crawler.pageparser.ComicPageParser;
import com.lbin.util.crawler.pageparser.SearchPageParser;
import com.lbin.util.crawler.util.CrawlerUtil;
import com.lbin.util.crawler.util.XxlCrawlerUtil;

import java.util.List;
import java.util.Map;


public class CrawlerApi {

    /**
     * demo 搜索
     * @param
     * @return
     */
    public static List<Search> Search(String url, Select select) {
        return CrawlerUtil.SearchByXXLCrawler(url,select,new SearchPageParser());
    }

    /**
     * demo 漫画pojo爬虫
     * @param url
     */
    public static Comic Comic(String url,Select select) {
        ComicPageParser comicPageParser = new ComicPageParser();
        ChapterPageParser chapterPageParser = new ChapterPageParser();
        Comic comic = CrawlerUtil.ComicByUrlCrawler(url, select,comicPageParser, chapterPageParser);
        return comic;
    }

    /**
     * demo 漫画pojo爬虫
     * @param url
     */
    public static ChapterImg ChapterImg(String url,Select select) {
        ChapterImgPageParser chapterPageParser = new ChapterImgPageParser();
        ChapterImg chapterImg = CrawlerUtil.ChapterImgByUrlCrawler(url, select,chapterPageParser);
        return chapterImg;
    }


    public static List<Map<String, Object>> getListMap(String url, Select select){
        MapPageParser mapPageParser = new MapPageParser();
        List<Map<String, Object>> maps = CrawlerUtil.ListMapByUrlCrawler(url, select, mapPageParser);
        return maps;
    }
    public static List<Map<String, Object>> getListMap(List<String> list, Select select){
        MapPageParser mapPageParser = new MapPageParser();
        List<Map<String, Object>> maps = CrawlerUtil.ListMapByUrlCrawler(list, select, mapPageParser);
        return maps;
    }
    public static Map<String, Object> getMap(String url, Select select){
        MapPageParser mapPageParser = new MapPageParser();
        Map<String, Object> map = CrawlerUtil.MapByUrlCrawler(url, select, mapPageParser);
        return map;
    }
    public static Map<String, Object> getMap(List<String> list, Select select){
        MapPageParser mapPageParser = new MapPageParser();
        Map<String, Object> map = CrawlerUtil.MapByUrlCrawler(list, select, mapPageParser);
        return map;
    }

}
