package com.lbin.util.crawler.util;

import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.util.FileUtil;

public class DownloadUtil {

    public static boolean DownloadImage(String url,String filePath,String type){
        String fileName = FileUtil.getFileNameByUrl(url, type);
        boolean ret = FileUtil.downFile(url, XxlCrawlerConf.TIMEOUT_MILLIS_DEFAULT, filePath, fileName);
        return ret;
    }
    public static boolean DownloadImage(String url,String filePath){
        String fileName = FileUtil.getFileNameByUrl(url, "jpg");
        boolean ret = FileUtil.downFile(url, XxlCrawlerConf.TIMEOUT_MILLIS_DEFAULT, filePath, fileName);
        return ret;
    }

}
