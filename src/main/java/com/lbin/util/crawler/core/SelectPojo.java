package com.lbin.util.crawler.core;

import lombok.Data;

import java.util.List;
@Data
public class SelectPojo {
    private String pageSelect;
    private List<FieldSelect> fieldSelectList;
}
