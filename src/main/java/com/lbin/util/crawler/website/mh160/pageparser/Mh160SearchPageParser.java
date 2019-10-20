package com.lbin.util.crawler.website.mh160.pageparser;

import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.website.mh160_2.mh160.Mh160SearchPojo;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

//搜索处理
public class Mh160SearchPageParser extends PageParser<Mh160SearchPojo> {
    private List<SearchPojo> searchPojoList = new ArrayList<>();

    @Override
    public void parse(Document document, Element element, Mh160SearchPojo mh160Search) {
        searchPojoList.add(mh160Search);
    }

    public List<SearchPojo> getSearchPojoList() {
        return searchPojoList;
    }

    public void setSearchPojoList(List<SearchPojo> searchPojoList) {
        this.searchPojoList = searchPojoList;
    }
}
