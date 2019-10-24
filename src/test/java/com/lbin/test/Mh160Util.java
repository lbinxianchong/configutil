package com.lbin.test;

import com.lbin.util.crawler.api.CrawlerApi;
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
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;


public class Mh160Util {


    /**
     * demo 搜索
     */
    @Test
    public void Mh160Search() {
        CrawlerApi crawlerApi = new CrawlerApi("Mh160");
        String search = "超人";
        int page = 1;
        List<String> list = new ArrayList<>();
        list.add(search);
        list.add(Integer.toString(page));
        List<SearchPojo> searchPojos = crawlerApi.Mh160Search(list);
        for (SearchPojo searchPojo : searchPojos) {
            System.out.println(searchPojo);
        }
    }


}
