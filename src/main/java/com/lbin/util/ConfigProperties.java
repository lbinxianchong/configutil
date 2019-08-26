package com.lbin.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * 配置文件读取工具
 */
public class ConfigProperties {
    private static Map<String, String> proertiesMap = null;

    /**
     * 
     * @参数  
     * @返回值 java.util.Map<java.lang.String,java.lang.String>
     * @开发者 lbin
     * @更新时间 2019/8/21 16:12 
     * @描述 获取ProertiesMap集合
     */
    private static Map<String, String> getProertiesMap() {
        Map<String, String> map = new HashMap<String, String>();
        Properties properties = new Properties();
        try {
            InputStream inputStream = Object.class.getResourceAsStream("/util.properties");
            properties.load(inputStream);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Set<Object> set = properties.keySet();
        for (Object o : set) {
            String key = (String) o;
            map.put(key, properties.getProperty(key));
        }
        return map;
    }
    /**
     * 
     * @参数 config 
     * @返回值 java.lang.String
     * @开发者 lbin
     * @更新时间 2019/8/21 16:13 
     * @描述 获取ProertiesMap集合value数值
     */
    public static String getConfig(String config) {
        if (proertiesMap == null) {
            proertiesMap = getProertiesMap();
        }
        return proertiesMap.get(config);
    }
}
