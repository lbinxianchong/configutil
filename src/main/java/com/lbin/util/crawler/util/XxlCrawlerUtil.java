package com.lbin.util.crawler.util;

import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.core.XxlCrawlerG;
import com.lbin.util.crawler.core.select.Select;
import com.xuxueli.crawler.XxlCrawler;
import com.xuxueli.crawler.loader.PageLoader;
import com.xuxueli.crawler.loader.strategy.HtmlUnitPageLoader;
import com.xuxueli.crawler.loader.strategy.JsoupPageLoader;
import com.xuxueli.crawler.loader.strategy.SeleniumPhantomjsPageLoader;
import com.xuxueli.crawler.parser.PageParser;

import java.util.List;

public class XxlCrawlerUtil {

    //不支持js，速度最快
    private static PageLoader jsoupPageLoader=new JsoupPageLoader();
    //支持动态js
    private static PageLoader htmlUnitPageLoader=new HtmlUnitPageLoader();
    //支持动态js，需要插件Phantomjs
    private static PageLoader seleniumPhantomjsPageLoader=new SeleniumPhantomjsPageLoader(CrawlerConfig.PhantomjsPATH);


    /**
     * jsoup
     *
     */
    public static void jsoupCrawle(boolean sync,Select select, PageParser pageParser, String... urls) {
        singleCrawle(sync,select,jsoupPageLoader,pageParser,urls);
    }

    /**
     * htmlUnit
     *
     */
    public static void htmlUnitCrawle(boolean sync,Select select, PageParser pageParser, String... urls) {
        singleCrawle(sync,select,htmlUnitPageLoader,pageParser,urls);
    }


    /**
     * Selenium+Phantomjs
     *
     */
    public static void SeleniumPhantomjsCrawle(boolean sync,Select select, PageParser pageParser, String... urls) {
        singleCrawle(sync,select,seleniumPhantomjsPageLoader,pageParser,urls);
    }


    /**
     * 单线程爬虫
     *
     */
    public static void singleCrawle(boolean sync,Select select, PageLoader pageLoader, PageParser pageParser, String... urls) {
        crawleG(sync,false,1,select,pageLoader,pageParser,urls);
    }

    /**
     * 爬虫主程序
     * @param allowSpread
     * @param threadCount
     * @param pageLoader
     * @param pageParser
     * @param urls
     */
    public static void crawle(boolean sync,boolean allowSpread,int threadCount,PageLoader pageLoader, PageParser pageParser,String... urls) {
        // 构造爬虫
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls(urls)
                .setAllowSpread(allowSpread)
                .setThreadCount(threadCount)
                .setPageLoader(pageLoader)
                .setPageParser(pageParser)
                .build();
        // 启动
        crawler.start(sync);
    }
    /**
     * 爬虫主程序(白名单)
     * @param allowSpread
     * @param threadCount
     * @param pageLoader
     * @param pageParser
     * @param urls
     */
    public static void crawle(boolean sync,List<String> whiteUrlRegexsList,boolean allowSpread,int threadCount,PageLoader pageLoader, PageParser pageParser,String... urls) {
        String[] whiteUrlRegexs=whiteUrlRegexsList.toArray(new String[whiteUrlRegexsList.size()]);
        // 构造爬虫
        XxlCrawler crawler = new XxlCrawler.Builder()
                .setUrls(urls)
                .setWhiteUrlRegexs(whiteUrlRegexs)
                .setAllowSpread(allowSpread)
                .setThreadCount(threadCount)
                .setPageLoader(pageLoader)
                .setPageParser(pageParser)
                .build();
        // 启动
        crawler.start(sync);
    }

    /**
     * 爬虫主程序
     * @param allowSpread
     * @param threadCount
     * @param pageLoader
     * @param pageParser
     * @param urls
     */
    public static void crawleG(boolean sync, boolean allowSpread, int threadCount, Select select, PageLoader pageLoader, PageParser pageParser, String... urls) {
        // 构造爬虫
        XxlCrawlerG crawler = new XxlCrawlerG.Builder()
                .setUrls(urls)
                .setAllowSpread(allowSpread)
                .setThreadCount(threadCount)
                .setSelect(select)
                .setPageLoader(pageLoader)
                .setPageParser(pageParser)
                .build();
        // 启动
        crawler.start(sync);
    }


}
