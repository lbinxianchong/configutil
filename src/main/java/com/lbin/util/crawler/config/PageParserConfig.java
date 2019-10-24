package com.lbin.util.crawler.config;


import com.lbin.util.crawler.pageparser.ChapterImgPageParser;
import com.lbin.util.crawler.pageparser.ChapterPageParser;
import com.lbin.util.crawler.pageparser.ComicPageParser;
import com.lbin.util.crawler.pageparser.SearchPageParser;

public class PageParserConfig {
    private String searchPath;
    private Class<? extends SearchPageParser> searchPageParser;
    private Class<? extends ComicPageParser> comicPageParser;
    private Class<? extends ChapterPageParser> chapterPageParser;
    private Class<? extends ChapterImgPageParser> chapterImgPageParser;

    public PageParserConfig() {
    }

    public PageParserConfig(String searchPath, Class<? extends SearchPageParser> searchPageParser, Class<? extends ComicPageParser> comicPageParser, Class<? extends ChapterPageParser> chapterPageParser, Class<? extends ChapterImgPageParser> chapterImgPageParser) {
        this.searchPath = searchPath;
        this.searchPageParser = searchPageParser;
        this.comicPageParser = comicPageParser;
        this.chapterPageParser = chapterPageParser;
        this.chapterImgPageParser = chapterImgPageParser;
    }

    public String getSearchPath() {
        return searchPath;
    }

    public void setSearchPath(String searchPath) {
        this.searchPath = searchPath;
    }

    public SearchPageParser getSearchPageParser() {
        try {
            return searchPageParser.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setSearchPageParser(Class searchPageParser) {
        this.searchPageParser = searchPageParser;
    }

    public ComicPageParser getComicPageParser() {
        try {
            return comicPageParser.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setComicPageParser(Class comicPageParser) {
        this.comicPageParser = comicPageParser;
    }

    public ChapterPageParser getChapterPageParser() {
        try {
            return chapterPageParser.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setChapterPageParser(Class chapterPageParser) {
        this.chapterPageParser = chapterPageParser;
    }

    public ChapterImgPageParser getChapterImgPageParser() {
        try {
            return chapterImgPageParser.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setChapterImgPageParser(Class chapterImgPageParser) {
        this.chapterImgPageParser = chapterImgPageParser;
    }
}
