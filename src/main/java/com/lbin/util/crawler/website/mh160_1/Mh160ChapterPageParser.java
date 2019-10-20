package com.lbin.util.crawler.website.mh160_1;

//import com.xianchong.privatespace_root.helper.CrawlerHelper;
import com.xuxueli.crawler.parser.PageParser;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

//章节处理
public class Mh160ChapterPageParser extends PageParser<Mh160ChapterImg> {
//    private CrawlerHelper crawlerHelper = new CrawlerHelper();
    @Override
    public void parse(Document document, Element element, Mh160ChapterImg mh160ChapterImg) {
//        crawlerHelper.Mh160ChapterImgDownload(mh160ChapterImg);
    }
}
