package com.lbin.util.sql;

//import com.lbin.util.chinesecharacter.ChineseCharacterUtil;
import com.lbin.util.jdbc.JDBCUtil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sqlprintf {
//    public static List<String> sqlp(String pathname){
//        List<String> list = new ArrayList<>();
//        try {
//            /* 读入TXT文件 */
//             // 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
//            File filename = new File(pathname); // 要读取以上路径的input。txt文件
//            InputStreamReader reader = new InputStreamReader(
//                    new FileInputStream(filename)); // 建立一个输入流对象reader
//            BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
//            String line = "";
//            line = br.readLine();
//            while (line != null) {
//                list.add(line);
//                line = br.readLine(); // 一次读入一行数据
//            }
//            list.remove(null);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//
//        return list;
//    }
//    public static void sqlSchoolJDBC(List<String> list){
//        List<Map<String,Object>> mapList= new ArrayList<>();
//        for (int i = 2; i < list.size(); i++) {
//            Map<String,Object> map=new HashMap<>();
//            map.put("schoolname",list.get(i));
//            map.put("id",i+1);
//            mapList.add(map);
//        }
//        try {
//            JDBCUtil.insertAll("match_school",mapList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//    public static void sqlcmsJDBC(List<String> list){
//        List<Map<String,Object>> mapList= new ArrayList<>();
//        for (int i = 0; i < list.size(); i++) {
//            String loginname = ChineseCharacterUtil.getLowerCase(list.get(i), false);
//            Map<String,Object> map=new HashMap<>();
//            map.put("loginname",loginname+"admin");
//            map.put("password","202cb962ac59075b964b07152d234b70");
//            map.put("schoolid",i+1);
//            map.put("id",i+4);
//            mapList.add(map);
//        }
//        try {
//            JDBCUtil.insertAll("cms_user",mapList);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//
//    public static void main(String[] args) {
//        List<String> sqlp = sqlp("F:\\workSpace\\configutil\\src\\main\\resources\\sql.txt");
//        for (String s : sqlp) {
//            System.out.println(s);
//        }
////        System.out.println(sqlp);
////        String s = ChineseCharacterUtil.getLowerCase("广西大学", false);
////        System.out.println(s);
//        sqlSchoolJDBC(sqlp);
////        sqlcmsJDBC(sqlp);
//    }
}
