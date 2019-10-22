package com.lbin.util.crawler.pageparser;


import com.lbin.util.crawler.model.Comic;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//漫画实体处理
public class ComicPageParser<T extends Comic> extends PageParser<T> {
    private Comic comic = new Comic();
    @Override
    public void parse(Document document, Element element, T t) {
        comic=t;
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}