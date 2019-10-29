package com.lbin.util.crawler.core.select;

import java.util.Map;

public class Select {
    private String pageSelect;
    private Map<String, FieldSelect> fieldSelectList;
    private Integer level;
    private Integer loader;

    public Select() {
        this.pageSelect = "html";
        this.level=2;
        this.loader=0;
    }

    public Select(String pageSelect, Map<String, FieldSelect> fieldSelectList, Integer level) {
        this.pageSelect = pageSelect;
        this.fieldSelectList = fieldSelectList;
        this.level = level;
    }

    public String getPageSelect() {
        return pageSelect;
    }

    public void setPageSelect(String pageSelect) {
        this.pageSelect = pageSelect;
    }

    public Map<String, FieldSelect> getFieldSelectList() {
        return fieldSelectList;
    }

    public void setFieldSelectList(Map<String, FieldSelect> fieldSelectList) {
        this.fieldSelectList = fieldSelectList;
    }
    // 0:定制化全静态
    // 1:半动态只要cssQuery，剩下留给model
    // 2:半动态全设置PageFieldSelect，剩下留给model
    // 3:全动态一定设置MapPageParser
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLoader() {
        return loader;
    }

    public void setLoader(Integer loader) {
        this.loader = loader;
    }
}
