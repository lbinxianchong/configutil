package com.lbin.util.crawler.website.mh160_2.mh160;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
//import com.xianchong.privatespace_root.helper.CrawlerHelper;


//漫画处理
@PipelineName("comicConsolePipeline")
public class ComicConsolePipeline implements Pipeline<Mh160Comic> {


//    private CrawlerHelper crawlerHelper = new CrawlerHelper();
    @Override
    public void process(Mh160Comic mh160Comic) {
        System.out.println(mh160Comic);
//        crawlerHelper.Mh160ComicDownload(mh160Comic);
//        XxlCrawlerUtil.Mh160ChapterCrawle(mh160Comic.getChapterurl());

    }
}
