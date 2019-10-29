package com.lbin.util.crawler.model;


import com.lbin.util.crawler.util.ModelUtil;
import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Comic implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    private String baseUrl;

    /**
     * 漫画标题
     */
    private String comicname;

    /**
     * 漫画封面图片url
     */
    private String coverimgurl;

    /**
     * 漫画封面图片本地
     */
    private String coverimglocal;

    /**
     * 漫画作者
     */
    private List<String> author;

    /**
     * 漫画类别
     */
    private List<String> category;

    /**
     * 漫画介绍
     */
    private String introduction;

    /**
     * 漫画章节实体
     */
    private List<Chapter> chapter;


    public List<String> getChapterUrl(){
        List<String> list=new ArrayList<>();
        for (int i = 0; i < chapter.size(); i++) {
            list.add(chapter.get(i).getChapterurl());
        }
        return list;
    }

}
