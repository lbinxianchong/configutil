package com.lbin.util.crawler.api;

import com.lbin.util.crawler.config.CrawlerConfig;
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
import com.xuxueli.crawler.loader.strategy.HtmlUnitPageLoader;
import com.xuxueli.crawler.loader.strategy.JsoupPageLoader;
import com.xuxueli.crawler.util.FileUtil;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


public class Mh160Util {


    /**
     * mh160 搜索
     * @param search
     * @return
     */
    public static List<SearchPojo> Mh160Search(String search, int page) {
        String url =CrawlerConfig.Mh160SearchPATH+"?key="+search+"&page="+page;
        return CrawlerUtil.SearchByXXLCrawler(url,new Mh160SearchPageParser());
    }
    /**
     * mh160 漫画pojo爬虫
     * @param url
     */
    public static Comic Mh160Comic(String url) {
        ComicPageParser comicPageParser = new Mh160ComicPageParser();
        ChapterPageParser chapterPageParser = new Mh160ChapterPageParser();
        Comic comic = CrawlerUtil.ComicByUrlCrawler(url, comicPageParser, chapterPageParser);
        return comic;
    }
    /**
     * mh160 漫画pojo爬虫
     * @param url
     */
    public static ChapterImg Mh160ChapterImg(String url) {
        ChapterImgPageParser chapterPageParser = new Mh160ChapterImgPageParser();
        ChapterImg chapterImg = CrawlerUtil.ChapterImgByUrlCrawler(url, chapterPageParser);
        return chapterImg;
    }



    /**
     * mh160 多个漫画pojo爬虫
     * @param urlList
     */
    public static void Mh160ComicByUrlCrawler(List<String> urlList) {

    }

    public static void main(String[] args) {

        /*List<SearchPojo> searchPojoList = Mh160SearchByUrlCrawlerXXL("世界",1);
        for (int i = 0; i < searchPojoList.size(); i++) {
            System.out.println(searchPojoList.get(i));
        }*/
        Comic comic = Mh160Comic("https://www.mh160.co/kanmanhua/31438/");
        List<String> chapterUrl = comic.getChapterUrl();
        ChapterImg chapterImg = Mh160ChapterImg(chapterUrl.get(0));
        String imgurl = chapterImg.getImgurl();
        System.out.println(imgurl);
        List<String> list = ModelUtil.ToUrlListString(imgurl, chapterImg.getNumber(), "jpg");
        System.out.println(list);
        if (imgurl.equals(list.get(0))){
            DownloadUtil.ComicDownload(list,chapterImg.getComicname(),chapterImg.getChaptername(),"jpg");
        }
    }
    //https://mhpic5.zjjdhs.cn/mh160tuku/s/射命丸文似乎拯救童话世界_31438/序话01_579177/0001.jpg
    //https://mhpic5.zjjdhs.cn/mh160tuku/s/射命丸文似乎拯救童话世界_31438/序话01_579177/0001.jpg
    public static void DemoCrawle() {
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls("https://manhua.dmzj.com/liangxiaofuwucai/72735.shtml")
                .setWhiteUrlRegexs("https://manhua.dmzj.com/liangxiaofuwucai/72735.shtml#@page=\\d+")
                .setAllowSpread(false)
                .setThreadCount(3)
//                .setPageParser(new ChapterPageParser())
                .build();
        crawler.start(true);
    }


}
