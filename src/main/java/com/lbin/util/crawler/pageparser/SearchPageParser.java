package com.lbin.util.crawler.pageparser;

import com.lbin.util.crawler.model.Search;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 搜索处理
 */
public class SearchPageParser extends PageParser<Search> {
    private List<Search> searchList = new ArrayList<>();

    @Override
    public void parse(Document document, Element element, Search t) {
        searchList.add(t);
    }

    public List<Search> getSearchList() {
        return searchList;
    }

    public void setSearchList(List<Search> searchList) {
        this.searchList = searchList;
    }

}
