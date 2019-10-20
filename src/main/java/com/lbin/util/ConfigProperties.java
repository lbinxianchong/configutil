package com.lbin.util;


import cn.hutool.core.collection.CollectionUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
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
            InputStreamReader isp=new InputStreamReader(inputStream,"UTF-8");
            properties.load(isp);
            inputStream.close();
            isp.close();
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

    /**
     *
     * @param annotation 注解
     * @param fieldName 属性名
     * @param value 修改的值
     * @throws NoSuchFieldException
     * @throws IllegalAccessException
     * @Description 修改注解属性
     */
    public static void setAnnotationValue(Annotation annotation, String fieldName, String value) throws NoSuchFieldException, IllegalAccessException {
        InvocationHandler handler = Proxy.getInvocationHandler(annotation);
        Field hField = handler.getClass().getDeclaredField("memberValues");
        hField.setAccessible(true);
        Map memberValues = (Map) hField.get(handler);
        memberValues.put(fieldName, value);
    }

    /**
     *
     * @return java.lang.String
     * @date 2019/8/26 15:05 value
     * @Description 获取ProertiesMap集合下载配置key对应的value
     */
    public static String getDownload() {
        if (proertiesMap == null) {
            proertiesMap = getProertiesMap();
        }
        String download = proertiesMap.get("download.type")+".download";
        return proertiesMap.get(download);
    }
    /**
     *
     * @return List
     * @date 2019/8/26 15:05 value
     * @Description 获取ProertiesMap集合下载配置key对应的List
     */
    public static List<String> getConfigList(String config) {
        if (proertiesMap == null) {
            proertiesMap = getProertiesMap();
        }
        String[] list = proertiesMap.get(config).split(",");
        List<String> configList = CollectionUtil.newArrayList(list);
        return configList;
    }

    /**
     *
     * @param config 配置key
     * @return java.lang.String
     * @date 2019/8/26 15:05 value
     * @Description 获取ProertiesMap集合配置key对应的value
     */
    public static Long getConfigLong(String config) {
        if (proertiesMap == null) {
            proertiesMap = getProertiesMap();
        }
        return Long.valueOf(proertiesMap.get(config));
    }
    /**
     *
     * @return java.util.Map<java.lang.String,java.lang.String>
     * @date 2019/8/26 15:03
     * @Description 获取ProertiesMap集合
     */

    private static Map<String, String> getSqlProertiesMap(String sqllProerties) {
        Map<String, String> map = new HashMap<String, String>();
        Properties properties = new Properties();
        try {
            InputStream inputStream = Object.class.getResourceAsStream(sqllProerties);
            InputStreamReader isp=new InputStreamReader(inputStream,"UTF-8");
            properties.load(isp);
            inputStream.close();
            isp.close();
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
}
