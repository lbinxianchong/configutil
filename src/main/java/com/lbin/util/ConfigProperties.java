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
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @date 2019/8/26 15:03
     * @Description 获取ProertiesMap集合
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
     * @param config 配置key
     * @return java.lang.String
     * @date 2019/8/26 15:05 value
     * @Description 获取ProertiesMap集合配置key对应的value
     */
    public static String getConfig(String config) {
        if (proertiesMap == null) {
            proertiesMap = getProertiesMap();
        }
        return proertiesMap.get(config);
    }
}
