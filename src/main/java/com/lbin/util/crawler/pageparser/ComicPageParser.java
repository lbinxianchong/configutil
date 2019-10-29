package com.lbin.util.crawler.pageparser;


import com.lbin.util.crawler.model.Comic;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

/**
 * 漫画处理
 */
public class ComicPageParser extends PageParser<Comic> {
    private Comic comic = new Comic();
    @Override
    public void parse(Document document, Element element, Comic t) {
        this.comic=t;
        this.comic.setBaseUrl(element.baseUri());
    }

    public Comic getComic() {
        return comic;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }
}
