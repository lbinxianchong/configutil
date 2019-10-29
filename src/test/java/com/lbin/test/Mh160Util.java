package com.lbin.test;

import cn.hutool.core.bean.BeanUtil;
import com.lbin.util.crawler.api.CrawlerApi;
import com.lbin.util.crawler.config.CrawlerConfig;
import com.lbin.util.crawler.core.XxlCrawlerG;
import com.lbin.util.crawler.core.pageparser.MapPageParser;
import com.lbin.util.crawler.core.select.FieldSelect;
import com.lbin.util.crawler.core.select.Select;
import com.lbin.util.crawler.model.Comic;
import com.lbin.util.crawler.model.Search;
import com.lbin.util.crawler.core.pageparser.ModelPageParser;
import com.lbin.util.crawler.pageparser.SearchPageParser;
import com.lbin.util.crawler.util.ModelUtil;
import com.lbin.util.crawler.util.XxlCrawlerUtil;
import com.xuxueli.crawler.loader.strategy.JsoupPageLoader;
import lombok.Data;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.*;


public class Mh160Util {


    /**
     * demo 搜索
     */
    @Test
    public void Mh160Search() {
        String search = "超人";
        int page = 1;
        List<String> list = new ArrayList<>();
        list.add(search);
        list.add(Integer.toString(page));

        Select select = new Select();
        select.setPageSelect(".mh-search-result > ul > li");
        select.setLevel(2);

        Map<String, FieldSelect> map=new HashMap<>();
        FieldSelect urls = new FieldSelect();
        urls.setCssQuery(".mh-works-info > div > h4 > a");
        urls.setSelectType("ATTR");
        urls.setSelectVal("abs:href");
        FieldSelect names = new FieldSelect();
        names.setCssQuery(".mh-works-info > div > h4 > a");
        FieldSelect urlimgs = new FieldSelect();
        urlimgs.setCssQuery(".mh-worksbox > div > div > a > img");
        urlimgs.setSelectType("ATTR");
        urlimgs.setSelectVal("abs:src");
        FieldSelect chapterlasts = new FieldSelect();
        chapterlasts.setCssQuery(".mh-worksbox > div > p > a > span");

        map.put("url",urls);
        map.put("name",names);
        map.put("urlimg",urlimgs);
        map.put("chapterlast",chapterlasts);
        select.setFieldSelectList(map);

        String url= CrawlerConfig.Mh160SearchPATH;

        SearchPageParser modelPageParser = new SearchPageParser();
        for (String s : list) {
            url = url.replaceFirst("%pram%", s);
        }
        // 构造爬虫
        XxlCrawlerG crawler = new XxlCrawlerG.Builder()
                .setUrls(url)
                .setAllowSpread(false)
                .setThreadCount(1)
                .setSelect(select)
                .setPageLoader(new JsoupPageLoader())
                .setPageParser(modelPageParser)
                .build();
        // 启动
        crawler.start(true);
        List<Search> searches = modelPageParser.getSearchList();
        for (Search searchPojo : searches) {
            System.out.println(searchPojo);
        }
    }

    @Test
    public void Mh160Search1() {
        String search = "超人";
        int page = 1;
        List<String> list = new ArrayList<>();
        list.add(search);
        list.add(Integer.toString(page));

        Select select = new Select();
        select.setPageSelect(".mh-search-result > ul > li");
        select.setLevel(3);

        Map<String, FieldSelect> map=new HashMap<>();
        FieldSelect urls = new FieldSelect();
        urls.setCssQuery(".mh-works-info > div > h4 > a");
        urls.setSelectType("ATTR");
        urls.setSelectVal("abs:href");
        urls.setParmname("urls");
        urls.setParmClass(String.class);
        FieldSelect names = new FieldSelect();
        names.setCssQuery(".mh-works-info > div > h4 > a");
        names.setParmname("names");
        names.setParmClass(String.class);
        FieldSelect urlimgs = new FieldSelect();
        urlimgs.setCssQuery(".mh-worksbox > div > div > a > img");
        urlimgs.setSelectType("ATTR");
        urlimgs.setSelectVal("abs:src");
        urlimgs.setParmname("urlimgs");
        urlimgs.setParmClass(String.class);
        FieldSelect chapterlasts = new FieldSelect();
        chapterlasts.setCssQuery(".mh-worksbox > div > p > a > span");
        chapterlasts.setParmname("chapterlasts");
        chapterlasts.setParmClass(String.class);

        map.put("url",urls);
        map.put("name",names);
        map.put("urlimg",urlimgs);
        map.put("chapterlast",chapterlasts);
        select.setFieldSelectList(map);

        String url= CrawlerConfig.Mh160SearchPATH;

        MapPageParser modelPageParser = new MapPageParser();
        for (String s : list) {
            url = url.replaceFirst("%pram%", s);
        }
        // 构造爬虫
        XxlCrawlerG crawler = new XxlCrawlerG.Builder()
                .setUrls(url)
                .setAllowSpread(false)
                .setThreadCount(1)
                .setSelect(select)
                .setPageLoader(new JsoupPageLoader())
                .setPageParser(modelPageParser)
                .build();
        // 启动
        crawler.start(true);
        List<Map<String, Object>> searches = modelPageParser.getModelList();
        /*for (Map<String, Object> stringObjectMap : searches) {
            Set<Map.Entry<String, Object>> entries = stringObjectMap.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                Object value = entry.getValue();
                System.out.print(entry.getKey());
                System.out.print("--"+value);
                System.out.println();
            }
        }*/

        for (Map<String, Object> objectMap : searches) {
            Search search1 = BeanUtil.mapToBean(objectMap, Search.class, true);
            System.out.println(search1);
        }

    }
    @Test
    public void Mh160Search2() {

        //-----参数
        String search = "异世界";
        int page = 1;
        List<String> list = new ArrayList<>();
        list.add(search);
        list.add(Integer.toString(page));
        //-----参数

        //-----传参
        Select select = ModelUtil.beanToSelect(SearchT.class,2);
        String url= CrawlerConfig.Mh160SearchPATH;
        url = ModelUtil.listInString(list, url);
        //-----传参

        List<Map<String, Object>> listMap = CrawlerApi.getListMap(url, select);

        List<Search> searches1 = ModelUtil.listMapToListBean(listMap, Search.class);
        for (Search search1 : searches1) {
            System.out.println(search1);
        }

    }

    @Test
    public void Mh160Test1() {
        Class pageVoClassType = Comic.class;

        Field[] fields = pageVoClassType.getDeclaredFields();
        int i =5;
        Class<?> fieldType = fields[i].getType();
        Type genericType = fields[i].getGenericType();
        System.out.println(fields[i].getName());
        System.out.println(genericType);
        System.out.println(fields[i].getDeclaringClass());
        System.out.println(fields[i].getType());
//        System.out.println(fields[2].getGenericType());

        // parse list item
        if (fields[i].getGenericType() instanceof ParameterizedType) {
            ParameterizedType fieldGenericType = (ParameterizedType) fields[i].getGenericType();
            System.out.println(fieldGenericType);
            if (fieldGenericType.getRawType().equals(List.class)) {
                Type gtATA = fieldGenericType.getActualTypeArguments()[0];
                fieldType = (Class<?>) gtATA;
            }
        }
        System.out.println(fieldType);
    }
    @Test
    public void Mh160Test2() {
        Map<String,Object> map = new HashMap<>();
        String s1="1";
        map.put("s1",s1);
        List<String> l1=new ArrayList<>();
        l1.add(" 23 ");
        l1.add(" 43");
        map.put("l1",l1);
        Modeltest modeltest = BeanUtil.mapToBean(map, Modeltest.class, true);
        System.out.println(modeltest);

    }



}
