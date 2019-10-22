package com.lbin.util.crawler.api;

import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.pageparser.SearchPageParser;
import com.lbin.util.crawler.util.GeccoUtil;
import com.lbin.util.crawler.util.XxlCrawlerUtil;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160SearchPageParser;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.loader.strategy.HtmlUnitPageLoader;

import java.util.ArrayList;
import java.util.List;


public class Mh160Util {

    /**
     * mh160_1 漫画pojo爬虫
     * @param url
     */
    public static void Mh160ComicByUrlCrawler(String url) {
       GeccoUtil.ComicDownload(url,CrawlerConfig.Mh160ComicPATH);
    }
    /**
     * mh160_1 多个漫画pojo爬虫
     * @param urlList
     */
    public static void Mh160ComicByUrlCrawler(List<String> urlList) {
        GeccoUtil.ComicDownload(urlList,CrawlerConfig.Mh160ComicPATH);
    }

    /**
     * mh160_1 搜索
     * @param search
     * @return
     */
    /*public static List<SearchPojo> Mh160SearchByUrlCrawler(String search, int page) {
        String url ="https://www.mh160_1.co/statics/search.aspx?key="+search+"&page="+page;
        return GeccoUtil.SearchByUrlCrawler(url, CrawlerConfig.Mh160ComicPATH);
    }*/
    /**
     * mh160_1 搜索
     * @param search
     * @return
     */
    public static List<SearchPojo> Mh160SearchByUrlCrawlerXXL(String search, int page) {
        String url =CrawlerConfig.Mh160SearchPATH+"?key="+search+"&page="+page;
        Mh160SearchPageParser searchPageParser=new Mh160SearchPageParser();
        XxlCrawlerUtil.SingleCrawle(true,url,new HtmlUnitPageLoader(),searchPageParser);
        return searchPageParser.getSearchPojoList();
    }


    public static void main(String[] args) {


//        List<SearchPojo> pojos = Mh160SearchByUrlCrawlerXXL("世界",1);
        String url ="https://www.mh160.co/statics/search.aspx?key=智能&page=1";
        SearchPageParser searchPageParser=new Mh160SearchPageParser();
        // 构造爬虫
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls(url)
                .setAllowSpread(false)
                .setThreadCount(1)
                .setPageParser(searchPageParser)
                .build();
        // 启动
        crawler.start(true);
        List<SearchPojo> searchPojoList = searchPageParser.getSearchPojoList();
        List<String> urlList=new ArrayList<>();
        for (int i = 0; i < searchPojoList.size(); i++) {
            System.out.println(searchPojoList.get(0));
        }

//        Mh160ComicByUrlCrawler(urlList);
    }


}
