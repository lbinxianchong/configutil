package com.lbin.util.crawler.core.select;

import com.xuxueli.crawler.conf.XxlCrawlerConf;
import lombok.Data;


public class FieldSelect {
    private String parmname;
    private String cssQuery;
    private String selectType;
    private String selectVal;
    private String datePattern;
    //level>2才使用设置参数类型，集合用List
    private Class parmClass;

    public FieldSelect() {
        this.cssQuery = "";
        this.selectType = "TEXT";
        this.selectVal = "";
        this.datePattern = "yyyy-MM-dd HH:mm:ss";
    }

    public FieldSelect(String parmname, String cssQuery, String selectType, String selectVal) {
        this.parmname = parmname;
        this.cssQuery = cssQuery;
        this.selectType = selectType;
        this.selectVal = selectVal;
    }

    public String getParmname() {
        return parmname;
    }

    public void setParmname(String parmname) {
        this.parmname = parmname;
    }

    public String getCssQuery() {
        return cssQuery;
    }

    public void setCssQuery(String cssQuery) {
        this.cssQuery = cssQuery;
    }

    public XxlCrawlerConf.SelectType getSelectType() {
        if ("HTML".equals(selectType)){
            return XxlCrawlerConf.SelectType.HTML;
        }else if ("VAL".equals(selectType)){
            return XxlCrawlerConf.SelectType.VAL;
        }else if ("TEXT".equals(selectType)){
            return XxlCrawlerConf.SelectType.TEXT;
        }else if ("TOSTRING".equals(selectType)){
            return XxlCrawlerConf.SelectType.TOSTRING;
        }else if ("ATTR".equals(selectType)){
            return XxlCrawlerConf.SelectType.ATTR;
        }else if ("HAS_CLASS".equals(selectType)){
            return XxlCrawlerConf.SelectType.HAS_CLASS;
        }else {
            return XxlCrawlerConf.SelectType.TEXT;
        }
    }

    public void setSelectType(String selectType) {
        this.selectType = selectType;
    }
    public void setSelectType(XxlCrawlerConf.SelectType selectType) {
        if (XxlCrawlerConf.SelectType.HTML == selectType){
            this.selectType = "HTML";
        }else if (XxlCrawlerConf.SelectType.VAL == selectType){
            this.selectType = "VAL";
        }else if (XxlCrawlerConf.SelectType.TEXT == selectType){
            this.selectType = "TEXT";
        }else if (XxlCrawlerConf.SelectType.TOSTRING == selectType){
            this.selectType = "TOSTRING";
        }else if (XxlCrawlerConf.SelectType.ATTR == selectType){
            this.selectType = "ATTR";
        }else if (XxlCrawlerConf.SelectType.HAS_CLASS == selectType){
            this.selectType = "HAS_CLASS";
        }else {
            this.selectType = "TEXT";
        }
    }

    public String getSelectVal() {
        return selectVal;
    }

    public void setSelectVal(String selectVal) {
        this.selectVal = selectVal;
    }

    public Class getParmClass() {
        return parmClass;
    }

    public void setParmClass(Class parmClass) {
        this.parmClass = parmClass;
    }

    public String getDatePattern() {
        return datePattern;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    @Override
    public String toString() {
        return "FieldSelect{" +
                "parmname='" + parmname + '\'' +
                ", cssQuery='" + cssQuery + '\'' +
                ", selectType='" + selectType + '\'' +
                ", selectVal='" + selectVal + '\'' +
                '}';
    }

}
