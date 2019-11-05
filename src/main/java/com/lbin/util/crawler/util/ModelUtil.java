package com.lbin.util.crawler.util;

import cn.hutool.core.bean.BeanUtil;
import com.lbin.util.crawler.core.select.FieldSelect;
import com.lbin.util.crawler.core.select.Select;
import com.lbin.util.crawler.model.Chapter;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.util.FieldReflectionUtil;

import javax.xml.crypto.Data;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

//通用参数操作工具
public class ModelUtil {
    public static String ToString(String parm) {
        if (parm.isEmpty()) {
            parm = "未知";
        }
        return parm;
    }

    public static List<String> ToListString(List<String> parm) {
        if (parm.isEmpty()) {
            List<String> list = new ArrayList<>();
            list.add("未知");
            parm = list;
        }
        return parm;
    }

    public static String ToString() {
        return "未知";
    }

    public static List<String> ToListString() {
        List<String> list = new ArrayList<>();
        list.add("未知");
        return list;
    }

    //批量get方法
    public static  List<String>  getFieldValueToListString(List<Object> list,String fieldName){
        List<String> stringList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            String parm = (String) getFieldValueByName(fieldName,o);
            stringList.add(parm);
        }
        return stringList;
    }

    //get方法
    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[] {});
            Object value = method.invoke(o);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    //图片地址转换用
    public static List<String> ToUrlListString(String url,int number,String keep,String type){
        List<String> list=new ArrayList<>();
        int num=url.lastIndexOf("/");
        String suburl = url.substring(0,num+1);
        for (int i = 0; i < number; i++) {
            String str=String.format(keep,i+1);
            list.add(suburl+str+"."+type);
        }
        return list;
    }

    //CrawlerThreadG转换类型用
    public static Object parseValue(Class c, String datePattern,String value) {

        Class<?> fieldType = c;

        if(value==null || value.trim().length()==0)
            return null;
        value = value.trim();

        if (Byte.class.equals(fieldType) || Byte.TYPE.equals(fieldType)) {
            return FieldReflectionUtil.parseByte(value);
        } else if (Boolean.class.equals(fieldType) || Boolean.TYPE.equals(fieldType)) {
            return FieldReflectionUtil.parseBoolean(value);
        } else if (String.class.equals(fieldType)) {
            return value;
        }else if (List.class.equals(fieldType)) {
            return value;
        } else if (Short.class.equals(fieldType) || Short.TYPE.equals(fieldType)) {
            return FieldReflectionUtil.parseShort(value);
        } else if (Integer.class.equals(fieldType) || Integer.TYPE.equals(fieldType)) {
            return FieldReflectionUtil.parseInt(value);
        } else if (Long.class.equals(fieldType) || Long.TYPE.equals(fieldType)) {
            return FieldReflectionUtil.parseLong(value);
        } else if (Float.class.equals(fieldType) || Float.TYPE.equals(fieldType)) {
            return FieldReflectionUtil.parseFloat(value);
        } else if (Double.class.equals(fieldType) || Double.TYPE.equals(fieldType)) {
            return FieldReflectionUtil.parseDouble(value);
        } else if (Date.class.equals(fieldType)) {
            return parseDate(datePattern, value);
        } else {
            throw new RuntimeException("request illeagal type, type must be Integer not int Long not long etc, type=" + fieldType);
        }
    }

    private static Date parseDate(String date, String value) {
        try {
            String datePattern = "yyyy-MM-dd HH:mm:ss";
            if (datePattern != null) {
                datePattern = date;
            }
            SimpleDateFormat dateFormat = new SimpleDateFormat(datePattern);
            return dateFormat.parse(value);
        } catch(ParseException e) {
            throw new RuntimeException("parseDate but input illegal input=" + value, e);
        }
    }

    //bean转换Select
    public static Select beanToSelect(Class pageVoClassType,Integer level){
        Select select = new Select();
        select.setLevel(level);
        PageSelect pageVoSelect = (PageSelect) pageVoClassType.getAnnotation(PageSelect.class);
        String pageVoCssQuery = (pageVoSelect!=null && pageVoSelect.cssQuery()!=null && pageVoSelect.cssQuery().trim().length()>0)?pageVoSelect.cssQuery():"html";
        select.setPageSelect(pageVoCssQuery);

        Map<String, FieldSelect> fieldSelectList = new HashMap<>();

        Field[] fields = pageVoClassType.getDeclaredFields();
        if (fields!=null) {
            for (Field field: fields) {
                FieldSelect fieldSelects =new FieldSelect();
                if (Modifier.isStatic(field.getModifiers())) {
                    continue;
                }

                String parmname = field.getName();
                String cssQuery = null;
                XxlCrawlerConf.SelectType selectType = null;
                String selectVal = null;
                String datePattern = null;
                Class<?> parmClass = field.getType();

                PageFieldSelect fieldSelect = field.getAnnotation(PageFieldSelect.class);
                if (fieldSelect != null) {
                    cssQuery = fieldSelect.cssQuery();
                    selectType = fieldSelect.selectType();
                    selectVal = fieldSelect.selectVal();
                    datePattern = fieldSelect.datePattern();
                }

                if (cssQuery==null || cssQuery.trim().length()==0) {
                    continue;
                }
                fieldSelects.setParmname(parmname);
                fieldSelects.setCssQuery(cssQuery);
                fieldSelects.setSelectType(selectType);
                fieldSelects.setSelectVal(selectVal);
                fieldSelects.setDatePattern(datePattern);
                fieldSelects.setParmClass(parmClass);

                fieldSelectList.put(parmname,fieldSelects);
            }
        }
        select.setFieldSelectList(fieldSelectList);
        return select;
    }

    //字符串集合注入字符串
    public static String listInString(List<String> list,String url){
        for (String s : list) {
            url = url.replaceFirst("%pram%", s);
        }
        return url;
    }
    //字符串集合注入字符串
    public static <T> List<T> listMapToListBean(List<Map<String, Object>> list,Class<T> c){
        List<T> mapList = new ArrayList<>();
        for (Map<String, Object> objectMap : list) {
            T t = BeanUtil.mapToBean(objectMap, c, true);
            mapList.add(t);
        }
        return mapList;
    }

    //编码转换iso8859-1,让浏览器能够识别
    public static String Codec (String s){
        try {
            s=new String(s.getBytes(),"iso8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return s;
    }

    public static Class toClass(String name){
        if ("String".equals(name)){
            return String.class;
        }else if ("Byte".equals(name)){
            return Byte.class;
        }else if ("List".equals(name)){
            return List.class;
        }else if ("Boolean".equals(name)){
            return Boolean.class;
        }else if ("Short".equals(name)){
            return Short.class;
        }else if ("Integer".equals(name)){
            return Integer.class;
        }else if ("Long".equals(name)){
            return Long.class;
        }else if ("Float".equals(name)){
            return Float.class;
        }else if ("Double".equals(name)){
            return Double.class;
        }else if ("Date".equals(name)){
            return Date.class;
        }else {
            return String.class;
        }
    }




}
