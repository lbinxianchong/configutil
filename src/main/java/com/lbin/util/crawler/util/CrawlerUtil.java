package com.lbin.util.crawler.util;

import com.lbin.util.crawler.core.pageparser.MapPageParser;
import com.lbin.util.crawler.core.select.Select;
import com.lbin.util.crawler.model.Chapter;
import com.lbin.util.crawler.model.ChapterImg;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.model.Search;
import com.lbin.util.crawler.pageparser.ChapterImgPageParser;
import com.lbin.util.crawler.pageparser.ChapterPageParser;
import com.lbin.util.crawler.pageparser.ComicPageParser;
import com.lbin.util.crawler.pageparser.SearchPageParser;
import com.xuxueli.crawler.parser.PageParser;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CrawlerUtil {


    /**
     * jsoup搜索（速度最快，默认）
     * @param searchurl
     * @param searchPageParser
     * @return
     */
    public static List<Search> SearchByXXLCrawler(String searchurl, Select select, SearchPageParser searchPageParser) {
        XxlCrawlerUtil.jsoupCrawle(true,select,searchPageParser,searchurl);
        return searchPageParser.getSearchList();
    }

    /**
     * HtmlUnit搜索
     * @param searchurl
     * @param searchPageParser
     * @return
     */
    public static List<Search> SearchByXXLCrawlerAndHtmlUnit(String searchurl,Select select, SearchPageParser searchPageParser) {
        searchurl = ModelUtil.Codec(searchurl);
        XxlCrawlerUtil.htmlUnitCrawle(true,select,searchPageParser,searchurl);
        return searchPageParser.getSearchList();
    }
    /**
     * 漫画pojo爬虫
     * @param url
     */
    public static Comic ComicByUrlCrawler(String url,Select select, ComicPageParser comicPageParser, ChapterPageParser chapterPageParser) {

        XxlCrawlerUtil.jsoupCrawle(true,select,comicPageParser,url);
        Comic comic = comicPageParser.getComic();

        XxlCrawlerUtil.jsoupCrawle(true,select,chapterPageParser,url);
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

    public static ChapterImg ChapterImgByUrlCrawler(String url,Select select, ChapterImgPageParser chapterImgPageParser){
        XxlCrawlerUtil.htmlUnitCrawle(true,select,chapterImgPageParser,url);
        ChapterImg chapterImg = chapterImgPageParser.getChapterImg();
        return chapterImg;
    }

    public static List<ChapterImg> ChapterImgByUrlCrawler(List<String> list,Select select, ChapterImgPageParser chapterImgPageParser){
        String[] urls=list.toArray(new String[list.size()]);
        XxlCrawlerUtil.htmlUnitCrawle(true,select,chapterImgPageParser,urls);
        List<ChapterImg> chapterImgList = chapterImgPageParser.getChapterImgList();
        return chapterImgList;
    }

    public static ChapterImg DownloadChapterImg(ChapterImg chapterImg,String type){
        List<String> imgurls = chapterImg.getImgurls();
        String imgurl = chapterImg.getImgurl();
        List<String> imglocals= new ArrayList<>();
        if (imgurls ==null){
            imgurls = ModelUtil.ToUrlListString(imgurl, chapterImg.getNumber(), "%04d","jpg");
            if (chapterImg.getImgurl().equals(imgurls.get(0))){
                imglocals=DownloadUtil.ComicDownload(imgurls,chapterImg.getComicname(),chapterImg.getChaptername(),"jpg");
            }
        }else {
            imglocals=DownloadUtil.ComicDownload(imgurls,chapterImg.getComicname(),chapterImg.getChaptername(),"jpg");
        }
        chapterImg.setImglocals(imglocals);
        return chapterImg;
    }

    public static List<Map<String, Object>> ListMapByUrlCrawler(String url, Select select, MapPageParser mapPageParser){
        UrlCrawler(select,mapPageParser,url);
        return mapPageParser.getModelList();
    }
    public static List<Map<String, Object>> ListMapByUrlCrawler(List<String> list, Select select, MapPageParser mapPageParser){
        String[] urls=list.toArray(new String[list.size()]);
        UrlCrawler(select,mapPageParser,urls);
        return mapPageParser.getModelList();
    }
    public static Map<String, Object> MapByUrlCrawler(String url, Select select, MapPageParser mapPageParser){
        UrlCrawler(select,mapPageParser,url);
        return mapPageParser.getModel();
    }
    public static Map<String, Object> MapByUrlCrawler(List<String> list, Select select, MapPageParser mapPageParser){
        String[] urls=list.toArray(new String[list.size()]);
        UrlCrawler(select,mapPageParser,urls);
        return mapPageParser.getModel();
    }
    private static void UrlCrawler(Select select, PageParser PageParser,String...urls){
        Integer loader = select.getLoader();
        if (loader==null){
            XxlCrawlerUtil.jsoupCrawle(true,select,PageParser,urls);
        }else if (loader==0){
            XxlCrawlerUtil.jsoupCrawle(true,select,PageParser,urls);
        }else if (loader==1){
            XxlCrawlerUtil.htmlUnitCrawle(true,select,PageParser,urls);
        }else if (loader==2){
            XxlCrawlerUtil.SeleniumPhantomjsCrawle(true,select,PageParser,urls);
        }else {
            XxlCrawlerUtil.jsoupCrawle(true,select,PageParser,urls);
        }
    }

}
