package com.lbin.util.crawler.util;

import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.config.PageParserConfig;


public class ConfigUtil {
    /**
     * 添加爬虫实体-website copyDemo-定制化PageParser-CrawlerConfig-ConfigUtil.getConfig
     * @param config
     * @return
     */
    public static PageParserConfig getConfig(String config){
        if (config.equals("Mh160")){
            return CrawlerConfig.Mh160PageParserConfig;
        }else if (config.equals("Demo")){
            return CrawlerConfig.DemoPageParserConfig;
        }else {
            return null;
        }
    }
}
