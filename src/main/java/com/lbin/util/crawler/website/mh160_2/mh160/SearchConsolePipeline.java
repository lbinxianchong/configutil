package com.lbin.util.crawler.website.mh160_2.mh160;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.lbin.util.crawler.model.SearchPojo;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

//搜索处理
public class SearchConsolePipeline extends PageParser<Mh160SearchPojo> {
    public  List<SearchPojo> searchPojoList = new ArrayList<>();

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
