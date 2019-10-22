package com.lbin.util.crawler.pageparser;


import com.lbin.util.crawler.model.ChapterImg;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;


/**
 * 漫画图片处理
 */
public class ChapterImgPageParser<T extends ChapterImg> extends PageParser <T> {
    private ChapterImg chapterImg = new ChapterImg();

    @Override
    public void parse(Document document, Element element, T t) {
        this.chapterImg=t;
        this.chapterImg.setChapterimgurl(document.baseUri());
    }

    public ChapterImg getChapterImg() {
        return chapterImg;
    }

    public void setChapterImg(ChapterImg chapterImg) {
        this.chapterImg = chapterImg;
    }
}
