package com.lbin.util.crawler.pageparser;

import com.lbin.util.crawler.model.SearchPojo;
import com.xuxueli.crawler.parser.PageParser;
import org.apache.http.HttpRequest;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索处理
 */
public class SearchPageParser<T extends SearchPojo> extends PageParser<T> {
    private List<SearchPojo> searchPojoList = new ArrayList<>();

    @Override
    public void parse(Document document, Element element, T t) {
        searchPojoList.add(t);
    }

    public List<SearchPojo> getSearchPojoList() {
        return searchPojoList;
    }

    public void setSearchPojoList(List<SearchPojo> searchPojoList) {
        this.searchPojoList = searchPojoList;
    }

}
