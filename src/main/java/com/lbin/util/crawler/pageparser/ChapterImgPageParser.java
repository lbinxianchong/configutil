package com.lbin.util.crawler.pageparser;


import com.lbin.util.crawler.model.ChapterImg;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.util.ArrayList;
import java.util.List;


/**
 * 漫画图片处理
 */
public class ChapterImgPageParser extends PageParser <ChapterImg> {
    private ChapterImg chapterImg = new ChapterImg();
    private List<ChapterImg> chapterImgList = new ArrayList<>();

    @Override
    public void parse(Document document, Element element, ChapterImg t) {
        if (chapterImgList.size()<=0){
            this.chapterImg=t;
            this.chapterImg.setBaseUrl(element.baseUri());
        }
        chapterImgList.add(t);
    }

    public ChapterImg getChapterImg() {
        return chapterImg;
    }

    public void setChapterImg(ChapterImg chapterImg) {
        this.chapterImg = chapterImg;
    }

    public List<ChapterImg> getChapterImgList() {
        return chapterImgList;
    }

    public void setChapterImgList(List<ChapterImg> chapterImgList) {
        this.chapterImgList = chapterImgList;
    }
}
