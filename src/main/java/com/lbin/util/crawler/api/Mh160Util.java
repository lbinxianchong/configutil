package com.lbin.util.crawler.api;

import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.util.GeccoUtil;
import com.lbin.util.crawler.util.XxlCrawlerUtil;
import com.lbin.util.crawler.website.mh160_2.mh160.SearchConsolePipeline;
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
    public static List<SearchPojo> Mh160SearchByUrlCrawler(String search, int page) {
        String url ="https://www.mh160_1.co/statics/search.aspx?key="+search+"&page="+page;
        return GeccoUtil.SearchByUrlCrawler(url, CrawlerConfig.Mh160ComicPATH);
    }
    /**
     * mh160_1 搜索
     * @param search
     * @return
     */
    public static List<SearchPojo> Mh160SearchByUrlCrawlerXXL(String search, int page) {
        String url ="https://www.mh160_1.co/statics/search.aspx?key="+search+"&page="+page;
        SearchConsolePipeline searchConsolePipeline = new SearchConsolePipeline();
        XxlCrawlerUtil.SingleCrawle(true,url,new HtmlUnitPageLoader(),searchConsolePipeline);
        return searchConsolePipeline.getSearchPojoList();
    }


    public static void main(String[] args) {

        List<SearchPojo> pojos = Mh160SearchByUrlCrawlerXXL("世界",1);
        List<String> urlList=new ArrayList<>();
        for (int i = 0; i < pojos.size(); i++) {
//            urlList.add(pojos.get(i).getUrl());
            System.out.println(pojos.get(0));
        }

//        Mh160ComicByUrlCrawler(urlList);
    }


}
