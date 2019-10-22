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
//    private static PageLoader seleniumPhantomjsPageLoader=new SeleniumPhantomjsPageLoader(CrawlerConfig.PhantomjsPATH);
    private static PageLoader htmlUnitPageLoader=new HtmlUnitPageLoader();
    private static PageLoader jsoupPageLoader=new JsoupPageLoader();

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

    /**
     *
     * @param url
     * @param pageParser
     */
    public static void SeleniumPhantomjsCrawle(boolean sync,String url,PageParser pageParser) {
//        SingleCrawle(sync,url,seleniumPhantomjsPageLoader,pageParser);
    }

    /**
     *
     * @param urlList
     * @param pageParser
     */

    public static void SeleniumPhantomjsCrawle(boolean sync,List<String> urlList, PageParser pageParser) {
//        SingleCrawle(sync,urlList,seleniumPhantomjsPageLoader,pageParser);
    }

    /**
     * 单线程爬虫（单个url）
     * @param url
     * @param pageLoader
     * @param pageParser
     */
    public static void SingleCrawle(boolean sync,String url, PageLoader pageLoader, PageParser pageParser) {
        Crawle(sync,false,1,pageLoader,pageParser,url);
    }

    /**
     * 单线程爬虫（多个url）
     * @param urlList
     * @param pageLoader
     * @param pageParser
     */
    public static void SingleCrawle(boolean sync,List<String> urlList, PageLoader pageLoader, PageParser pageParser) {
        String[] urls=urlList.toArray(new String[urlList.size()]);
        Crawle(sync,false,1,pageLoader,pageParser,urls);
    }

    /**
     * 爬虫主程序(同步)
     * @param allowSpread
     * @param threadCount
     * @param pageLoader
     * @param pageParser
     * @param urls
     */
    public static void Crawle(boolean sync,boolean allowSpread,int threadCount,PageLoader pageLoader, PageParser pageParser,String... urls) {
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
    public static void Crawle(boolean sync,List<String> whiteUrlRegexsList,boolean allowSpread,int threadCount,PageLoader pageLoader, PageParser pageParser,String... urls) {
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
