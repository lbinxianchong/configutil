package com.lbin.util.crawler.website.mh160.pageparser;

//import com.xianchong.privatespace_root.helper.CrawlerHelper;
import com.lbin.util.crawler.website.mh160_1.Mh160ChapterImg;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//章节处理
public class Mh160ChapterImgPageParser extends PageParser<Mh160ChapterImg> {
//    private CrawlerHelper crawlerHelper = new CrawlerHelper();
    @Override
    public void parse(Document document, Element element, Mh160ChapterImg mh160ChapterImg) {
//        crawlerHelper.Mh160ChapterImgDownload(mh160ChapterImg);
    }
}
