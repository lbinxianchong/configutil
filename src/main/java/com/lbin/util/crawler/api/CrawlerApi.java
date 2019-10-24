package com.lbin.util.crawler.api;

import com.lbin.util.crawler.config.PageParserConfig;
import com.lbin.util.crawler.model.ChapterImg;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.pageparser.ChapterImgPageParser;
import com.lbin.util.crawler.pageparser.ChapterPageParser;
import com.lbin.util.crawler.pageparser.ComicPageParser;
import com.lbin.util.crawler.util.ConfigUtil;
import com.lbin.util.crawler.util.CrawlerUtil;

import java.util.List;


public class CrawlerApi {


    private PageParserConfig pageParserConfig;

    public CrawlerApi() {
    }

    public CrawlerApi(PageParserConfig pageParserConfig) {
        this.pageParserConfig = pageParserConfig;
    }

    public CrawlerApi(String config) {
        this.pageParserConfig = ConfigUtil.getConfig(config);
    }

    public PageParserConfig getPageParserConfig() {
        return pageParserConfig;
    }

    public void setPageParserConfig(PageParserConfig pageParserConfig) {
        this.pageParserConfig = pageParserConfig;
    }

    public void setPageParserConfig(String config) {
        this.pageParserConfig = ConfigUtil.getConfig(config);
    }

    /**
     * demo 搜索
     * @param
     * @return
     */
    public List<SearchPojo> Mh160Search(List<String> list) {
        String url=pageParserConfig.getSearchPath();
        for (String s : list) {
            url = url.replaceFirst("%pram%", s);
        }
        return CrawlerUtil.SearchByXXLCrawler(url,pageParserConfig.getSearchPageParser());
    }

    /**
     * demo 漫画pojo爬虫
     * @param url
     */
    public Comic Mh160Comic(String url) {
        ComicPageParser comicPageParser = pageParserConfig.getComicPageParser();
        ChapterPageParser chapterPageParser = pageParserConfig.getChapterPageParser();
        Comic comic = CrawlerUtil.ComicByUrlCrawler(url, comicPageParser, chapterPageParser);
        return comic;
    }

    /**
     * demo 漫画pojo爬虫
     * @param url
     */
    public ChapterImg Mh160ChapterImg(String url) {
        ChapterImgPageParser chapterPageParser = pageParserConfig.getChapterImgPageParser();
        ChapterImg chapterImg = CrawlerUtil.ChapterImgByUrlCrawler(url, chapterPageParser);
        return chapterImg;
    }



}
