package com.lbin.util.crawler.util;

import com.lbin.util.crawler.config.CrawlerConfig;
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
//    private static PageLoader seleniumPhantomjsPageLoader=new SeleniumPhantomjsPageLoader(CrawlerConfig.PhantomjsPATH);


    /**
     * jsoup
     * @param url
     * @param pageParser
     */
    public static void jsoupCrawle(boolean sync,String url,PageParser pageParser) {
        singleCrawle(sync,url,jsoupPageLoader,pageParser);
    }

    /**
     * jsoup
     * @param urlList
     * @param pageParser
     */

    public static void jsoupCrawle(boolean sync,List<String> urlList, PageParser pageParser) {
        singleCrawle(sync,urlList,jsoupPageLoader,pageParser);
    }

    /**
     * htmlUnit
     * @param url
     * @param pageParser
     */
    public static void htmlUnitCrawle(boolean sync,String url,PageParser pageParser) {
        singleCrawle(sync,url,htmlUnitPageLoader,pageParser);
    }

    /**
     * htmlUnit
     * @param urlList
     * @param pageParser
     */

    public static void htmlUnitCrawle(boolean sync,List<String> urlList, PageParser pageParser) {
        singleCrawle(sync,urlList,htmlUnitPageLoader,pageParser);
    }
    /**
     * Selenium+Phantomjs
     * @param url
     * @param pageParser
     */
    public static void SeleniumPhantomjsCrawle(boolean sync,String url,PageParser pageParser) {
//        singleCrawle(sync,url,seleniumPhantomjsPageLoader,pageParser);
    }

    /**
     * Selenium+Phantomjs
     * @param urlList
     * @param pageParser
     */

    public static void SeleniumPhantomjsCrawle(boolean sync,List<String> urlList, PageParser pageParser) {
//        singleCrawle(sync,urlList,seleniumPhantomjsPageLoader,pageParser);
    }

    /**
     * 单线程爬虫（单个url）
     * @param url
     * @param pageLoader
     * @param pageParser
     */
    public static void singleCrawle(boolean sync,String url, PageLoader pageLoader, PageParser pageParser) {
        crawle(sync,false,1,pageLoader,pageParser,url);
    }

    /**
     * 单线程爬虫（多个url）
     * @param urlList
     * @param pageLoader
     * @param pageParser
     */
    public static void singleCrawle(boolean sync,List<String> urlList, PageLoader pageLoader, PageParser pageParser) {
        String[] urls=urlList.toArray(new String[urlList.size()]);
        crawle(sync,false,1,pageLoader,pageParser,urls);
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


}
