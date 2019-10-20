package com.lbin.util.crawler.website.mh160_1;

import com.geccocrawler.gecco.annotation.PipelineName;
import com.geccocrawler.gecco.pipeline.Pipeline;
import com.lbin.util.crawler.model.SearchPojo;

import java.util.ArrayList;
import java.util.List;

//搜索处理
@PipelineName("searchConsolePipeline")
public class SearchConsolePipeline implements Pipeline<Mh160Search> {
    public static List<SearchPojo> searchPojoList =new ArrayList<>();;

    @Override
    public void process(Mh160Search mh160Search) {
        if (searchPojoList.size()>0){
            searchPojoList=new ArrayList<>();
        }
        List<Mh160SearchPojo> searchList = mh160Search.getSearchPojo();
        for (Mh160SearchPojo searchPojo : searchList) {
            searchPojoList.add(searchPojo);
        }
    }

}
