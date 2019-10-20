package com.lbin.util.crawler.util;

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
}
