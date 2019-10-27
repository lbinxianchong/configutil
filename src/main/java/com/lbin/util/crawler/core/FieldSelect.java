package com.lbin.util.crawler.core;

import com.xuxueli.crawler.conf.XxlCrawlerConf;
import lombok.Data;


public class FieldSelect {
    private String parmname;
    private String cssQuery;
    private String selectType;
    private String selectVal;

    public FieldSelect() {
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

    public String getSelectVal() {
        return selectVal;
    }

    public void setSelectVal(String selectVal) {
        this.selectVal = selectVal;
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
