package com.lbin.util.crawler.config;

import com.lbin.util.crawler.website.demo.pageparser.DemoChapterImgPageParser;
import com.lbin.util.crawler.website.demo.pageparser.DemoChapterPageParser;
import com.lbin.util.crawler.website.demo.pageparser.DemoComicPageParser;
import com.lbin.util.crawler.website.demo.pageparser.DemoSearchPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ChapterImgPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ChapterPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160ComicPageParser;
import com.lbin.util.crawler.website.mh160.pageparser.Mh160SearchPageParser;

public interface CrawlerConfig {

    String Download = "";

    String PhantomjsPATH="E:/Development/plugins/phantomjs-2.1.1-windows/bin/phantomjs.exe";

    String Mh160ComicPATH="";

    String Mh160SearchPATH="https://www.mh160.co/statics/search.aspx?key=%pram%&page=%pram%";

    String Mh160PATH="https://www.mh160.co";

    PageParserConfig DemoPageParserConfig = new PageParserConfig(
            CrawlerConfig.Mh160SearchPATH,
            DemoSearchPageParser.class,
            DemoComicPageParser.class,
            DemoChapterPageParser.class,
            DemoChapterImgPageParser.class);

    PageParserConfig Mh160PageParserConfig = new PageParserConfig(
            CrawlerConfig.Mh160SearchPATH,
            Mh160SearchPageParser.class,
            Mh160ComicPageParser.class,
            Mh160ChapterPageParser.class,
            Mh160ChapterImgPageParser.class);


}
