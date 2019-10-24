package com.lbin.util.crawler.util;

import cn.hutool.core.collection.CollUtil;
import com.lbin.util.crawler.model.Chapter;
import com.lbin.util.crawler.model.ChapterImg;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.pageparser.ChapterImgPageParser;
import com.lbin.util.crawler.pageparser.ChapterPageParser;
import com.lbin.util.crawler.pageparser.ComicPageParser;
import com.lbin.util.crawler.pageparser.SearchPageParser;
import com.xuxueli.crawler.parser.PageParser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CrawlerUtil {


    /**
     * jsoup搜索（速度最快，默认）
     * @param searchurl
     * @param searchPageParser
     * @return
     */
    public static List<SearchPojo> SearchByXXLCrawler(String searchurl, SearchPageParser searchPageParser) {
        XxlCrawlerUtil.jsoupCrawle(true,searchurl,searchPageParser);
        return searchPageParser.getSearchPojoList();
    }

    /**
     * HtmlUnit搜索
     * @param searchurl
     * @param searchPageParser
     * @return
     */
    public static List<SearchPojo> SearchByXXLCrawlerAndHtmlUnit(String searchurl, SearchPageParser searchPageParser) {
        try {
            searchurl=new String(searchurl.getBytes(),"iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        XxlCrawlerUtil.htmlUnitCrawle(true,searchurl,searchPageParser);
        return searchPageParser.getSearchPojoList();
    }
    /**
     * 漫画pojo爬虫
     * @param url
     */
    public static Comic ComicByUrlCrawler(String url, ComicPageParser comicPageParser, ChapterPageParser chapterPageParser) {

        XxlCrawlerUtil.jsoupCrawle(true,url,comicPageParser);
        Comic comic = comicPageParser.getComic();

        XxlCrawlerUtil.jsoupCrawle(true,url,chapterPageParser);
        List<Chapter> chapterListList = chapterPageParser.getChapterListList();
        Collections.reverse(chapterListList);
        comic.setChapter(chapterListList);
        return comic;
    }
    /**
     * 多个漫画pojo爬虫
     * @param urlList
     */
    public static void ComicByUrlCrawler(List<String> urlList,ComicPageParser comicPageParser, ChapterPageParser chapterPageParser) {

    }

    public static ChapterImg ChapterImgByUrlCrawler(String url, ChapterImgPageParser chapterImgPageParser){
        XxlCrawlerUtil.htmlUnitCrawle(true,url,chapterImgPageParser);
        ChapterImg chapterImg = chapterImgPageParser.getChapterImg();
        return chapterImg;
    }

    public static List<ChapterImg> ChapterImgByUrlCrawler(List<String> list, ChapterImgPageParser chapterImgPageParser){
        XxlCrawlerUtil.htmlUnitCrawle(true,list,chapterImgPageParser);
        List<ChapterImg> chapterImgList = chapterImgPageParser.getChapterImgList();
        return chapterImgList;
    }

    public static ChapterImg DownloadChapterImg(ChapterImg chapterImg,String type){
        List<String> imgurls = chapterImg.getImgurls();
        String imgurl = chapterImg.getImgurl();
        List<String> imglocals= new ArrayList<>();
        if (imgurls.size()<=0){
            imgurls = ModelUtil.ToUrlListString(imgurl, chapterImg.getNumber(), "jpg");
            if (chapterImg.getImgurl().equals(imgurls.get(0))){
                imglocals=DownloadUtil.ComicDownload(imgurls,chapterImg.getComicname(),chapterImg.getChaptername(),"jpg");
            }
        }else {
            imglocals=DownloadUtil.ComicDownload(imgurls,chapterImg.getComicname(),chapterImg.getChaptername(),"jpg");
        }
        chapterImg.setImglocals(imglocals);
        return chapterImg;
    }





}
