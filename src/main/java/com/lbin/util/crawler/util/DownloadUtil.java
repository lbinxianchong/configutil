package com.lbin.util.crawler.util;

import com.lbin.util.ConfigProperties;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.util.FileUtil;

import java.util.ArrayList;
import java.util.List;

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

    public static List<String> ComicDownload(List<String> imgurls,String comicname,String chaptername,String type){
        String filePath = ConfigProperties.getDownload()+comicname+"\\"+chaptername+"\\";
        List<String> imglocals=new ArrayList<>();
        for (int i = 0; i < imgurls.size(); i++) {
            String s = imgurls.get(i);
            DownloadImage(s,filePath,type);
            String imglocal = filePath+FileUtil.getFileNameByUrl(s, "jpg");
            imglocals.add(imglocal);
        }
        return imglocals;
    }
    /*public static boolean ComicDownload(List<String> urls,String comicname,String chaptername){

    }*/

}
