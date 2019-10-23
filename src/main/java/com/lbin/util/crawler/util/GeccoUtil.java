package com.lbin.util.crawler.util;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.Text;
import com.lbin.util.crawler.model.SearchPojo;

import java.util.List;

/**
 * 已废弃
 */
public class GeccoUtil {

    /**
     *  漫画pojo爬虫下载
     * @param url
     * @param classpath
     */

    public static void ComicDownload(String url,String classpath) {
        //GeccoEngine表示爬虫引擎，通过create()初始化，通过start()/run()运行。可以配置一些启动参数如：扫描@Gecco注解的包名classpath；开始抓取的url地址star；抓取线程数thread；抓取完一个页面后的间隔时间interval(ms)等
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath(classpath)
                //开始抓取的页面地址
                .start(url)
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .start();
    }
    /**
     * 多个漫画pojo爬虫下载
     * @param urlList
     * @param classpath
     */
    public static void ComicDownload(List<String> urlList,String classpath) {
        String[] urls = urlList.toArray(new String[urlList.size()]);
        //GeccoEngine表示爬虫引擎，通过create()初始化，通过start()/run()运行。可以配置一些启动参数如：扫描@Gecco注解的包名classpath；开始抓取的url地址star；抓取线程数thread；抓取完一个页面后的间隔时间interval(ms)等
        GeccoEngine.create()
                //Gecco搜索的包路径
                .classpath(classpath)
                //开始抓取的页面地址
                .start(urls)
                //开启几个爬虫线程
                .thread(3)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                .start();
    }

    /**
     * 在线搜索
     * @param url
     * @param classpath
     * @return
     */
    /*public static List<SearchPojo> SearchByUrlCrawler(String url, String classpath) {
        GeccoEngine.create()
                .classpath(classpath)
                .start(url)
                .thread(1)
                .interval(2000)
                .run();
        return new List<SearchPojo>() {
        }
    }*/




}
