package com.lbin.util.crawler.pageparser;

import com.lbin.util.crawler.model.Chapter;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * 章节处理
 */
public class ChapterPageParser extends PageParser <Chapter> {
    private List<Chapter> chapterListList = new ArrayList<>();

    @Override
    public void parse(Document document, Element element, Chapter t) {
        chapterListList.add(t);
    }

    public List<Chapter> getChapterListList() {
        return chapterListList;
    }

    public void setChapterListList(List<Chapter> chapterListList) {
        this.chapterListList = chapterListList;
    }
}
