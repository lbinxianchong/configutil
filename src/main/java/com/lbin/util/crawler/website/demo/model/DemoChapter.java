package com.lbin.util.crawler.website.demo.model;

import com.lbin.util.crawler.model.Chapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;


@Data
@EqualsAndHashCode(callSuper = true)
public class DemoChapter extends Chapter implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 章节url
     */
    private String chapterurl;

    /**
     * 章节名字
     */
    private String chaptername;


}