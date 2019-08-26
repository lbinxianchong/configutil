package com.lbin.test;

import com.lbin.util.jdbc.JDBCUtil;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class test {
    public static void main(String[] args) {

    }
    @Test
    private static void testAll1() {

        long start = System.currentTimeMillis();
        try {
            for (int i = 0; i < 10000; i++) {
                Map<String, Object> map = new HashMap<>();
                map.put("emp_id", 1013);
                map.put("name", "JDBCUtil测试");
                map.put("job", "developer");
                map.put("salary", 10000);
                map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
                JDBCUtil.insert("emp_test3", map);
            }
            System.out.println("共耗时" + (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //共耗时44110
    }
    @Test
    private static void testAll2() {
        List<Map<String, Object>> datas = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("emp_id", 1013);
            map.put("name", "JDBCUtil测试");
            map.put("job", "developer");
            map.put("salary", 10000);
            map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
            datas.add(map);
        }
        try {
            long start = System.currentTimeMillis();
            JDBCUtil.insertAll("emp_test3", datas);
            System.out.println("共耗时" + (System.currentTimeMillis() - start));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //共耗时649
    }
    @Test
    /**
     * 测试插入
     */
    private static void testInsert() {
        Map<String, Object> map = new HashMap<>();
        map.put("emp_id", 1013);
        map.put("name", "JDBCUtil测试");
        map.put("job", "developer");
        map.put("salary", 10000);
        map.put("hire_date", new java.sql.Date(System.currentTimeMillis()));
        try {
            int count = JDBCUtil.insert("emp_test", map);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Test
    /**
     * 测试更新
     */
    private static void testUpdate() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "测试更新");

        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("emp_id", "1013");
        try {
            int count = JDBCUtil.update("emp_test", map, whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    /**
     * 测试删除
     */
    private static void testDelete() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("emp_id", 1013);
        whereMap.put("job", "developer");
        try {
            int count = JDBCUtil.delete("emp_test", whereMap);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    /**
     * 查询方式一
     */
    public static void testQuery1() {
        Map<String, Object> whereMap = new HashMap<>();
        whereMap.put("salary", "10000");
        try {
            JDBCUtil.query("emp_test", whereMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    /**
     * 查询方式二
     */
    public static void testQuery2() {
        String where = "job = ?  AND salary = ? ";
        String[] whereArgs = new String[]{"clerk", "3000"};

        try {
            List<Map<String, Object>> list = JDBCUtil.query("emp_test", where, whereArgs);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    /**
     * 查询方式三
     */
    public static void testQuery3() {
        try {
            List<Map<String, Object>> list = JDBCUtil.query("emp_test", false, null, null, null, null, null, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    /**
     * SQL注入问题
     */
    public static void query4() {
        String name = "'1' OR '1'='1'";
        String password = "'1' OR '1'='1'";

        String sql = "SELECT * FROM emp_test WHERE name = " + name + " and password = " + password;
        String where = "name = ?  AND password = ? ";
        String[] whereArgs = new String[]{name, password};

        try {
            JDBCUtil.query(sql);
            JDBCUtil.query("emp_test", false, null, where, whereArgs, null, null, null, null);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
