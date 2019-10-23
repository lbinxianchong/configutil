package com.lbin.util.crawler.util;

import com.lbin.util.crawler.model.Chapter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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

    public static  List<String>  getFieldValueToListString(List<Object> list,String fieldName){
        List<String> stringList=new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            Object o = list.get(i);
            String parm = (String) getFieldValueByName(fieldName,o);
            stringList.add(parm);
        }
        return stringList;
    }

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

    public static List<String> ToUrlListString(String url,int number,String type){
        List<String> list=new ArrayList<>();
        int num=url.lastIndexOf("/");
        String suburl = url.substring(0,num+1);
        for (int i = 0; i < number; i++) {
            String str=String.format("%04d",i+1);
            list.add(suburl+str+"."+type);
        }
        return list;
    }




}
