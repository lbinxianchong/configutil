package com.lbin.util.crawler.website.mh160.model;

import com.lbin.util.crawler.model.Chapter;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;


@Data
@EqualsAndHashCode(callSuper = true)
public class Mh160Chapter extends Chapter implements Serializable {
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