package com.lbin.util.crawler.website.mh160.pageparser;

import com.lbin.util.crawler.model.SearchPojo;
import com.lbin.util.crawler.website.mh160.model.Mh160Comic;
import com.lbin.util.crawler.website.mh160_2.mh160.Mh160SearchPojo;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

//漫画实体处理
public class Mh160ComicPageParser extends PageParser<Mh160Comic> {
    private List<SearchPojo> searchPojoList = new ArrayList<>();

    @Override
    public void parse(Document document, Element element, Mh160Comic mh160Comic) {
        System.out.println(mh160Comic.getComic());
    }
}
