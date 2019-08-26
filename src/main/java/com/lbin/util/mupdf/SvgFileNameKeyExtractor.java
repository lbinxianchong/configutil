package com.lbin.util.mupdf;

import java.util.function.ToIntFunction;

/**
 * svg文件名提取器
 *
 * @author xty
 * Created by xty on 2019/4/8.
 */
public class SvgFileNameKeyExtractor implements ToIntFunction<String> {

    public int applyAsInt(String value) {
        int end = value.lastIndexOf(".");
        int start = value.lastIndexOf("_");
        return Integer.parseInt(value.substring(start + 1, end));
    }
}
