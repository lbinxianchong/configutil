package com.lbin.util.crawler.model;

import com.lbin.util.crawler.util.ModelUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * 搜索实体
 */
@Data
public class SearchPojo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 漫画url
     */
    private String url;

    /**
     * 漫画标题
     */
    private String name;

    /**
     * 漫画封面图片url
     */
    private String urlimg;

    /**
     * 漫画最后章节
     */
    private String chapterlast;


    public SearchPojo getSearch(){
        SearchPojo search = new SearchPojo();
        search.setUrl(ModelUtil.ToString(getUrl()));
        search.setName(ModelUtil.ToString(getName()));
        search.setUrlimg(ModelUtil.ToString(getUrlimg()));
        search.setChapterlast(ModelUtil.ToString(getChapterlast()));
        return search;
    }
}
