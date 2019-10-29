package com.lbin.util.crawler.core;

import com.lbin.util.crawler.core.select.FieldSelect;
import com.lbin.util.crawler.core.select.Select;
import com.lbin.util.crawler.util.ModelUtil;
import com.xuxueli.crawler.annotation.PageFieldSelect;
import com.xuxueli.crawler.annotation.PageSelect;
import com.xuxueli.crawler.conf.XxlCrawlerConf;
import com.xuxueli.crawler.exception.XxlCrawlerException;
import com.xuxueli.crawler.model.PageRequest;
import com.xuxueli.crawler.parser.strategy.NonPageParser;
import com.xuxueli.crawler.thread.CrawlerThread;
import com.xuxueli.crawler.util.FieldReflectionUtil;
import com.xuxueli.crawler.util.JsoupUtil;
import com.xuxueli.crawler.util.UrlUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.*;
import java.net.Proxy;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * crawler thread
 *
 * @author xuxueli 2017-10-10 10:58:19
 */
public class CrawlerThreadG extends CrawlerThread {
    private static Logger logger = LoggerFactory.getLogger(CrawlerThreadG.class);

    private XxlCrawlerG crawler;
    private boolean running;
    private boolean toStop;
    public CrawlerThreadG(XxlCrawlerG crawler) {
        super(crawler);
        this.crawler = crawler;
        this.running = true;
        this.toStop = false;
    }
    public void toStop() {
        this.toStop = true;
    }
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {

        while (!toStop) {
            try {

                // ------- url ----------
                running = false;
                crawler.tryFinish();
                String link = crawler.getRunData().getUrl();
                running = true;
                logger.info(">>>>>>>>>>> xxl crawler, process link : {}", link);
                if (!UrlUtil.isUrl(link)) {
                    continue;
                }

                // failover
                for (int i = 0; i < (1 + crawler.getRunConf().getFailRetryCount()); i++) {

                    boolean ret = false;
                    try {
                        // make request
                        PageRequest pageRequest = makePageRequest(link);

                        // pre parse
                        crawler.getRunConf().getPageParser().preParse(pageRequest);

                        // parse
                        if (crawler.getRunConf().getPageParser() instanceof NonPageParser) {
                            ret = processNonPage(pageRequest);
                        } else if (crawler.getSelect().getLevel()!=null&&crawler.getSelect().getLevel()>2){
                            ret = processMapPage(pageRequest);
                        }else {
                            ret = processPage(pageRequest);
                        }
                    } catch (Throwable e) {
                        logger.info(">>>>>>>>>>> xxl crawler proocess error.", e);
                    }

                    if (crawler.getRunConf().getPauseMillis() > 0) {
                        try {
                            TimeUnit.MILLISECONDS.sleep(crawler.getRunConf().getPauseMillis());
                        } catch (InterruptedException e) {
                            logger.info(">>>>>>>>>>> xxl crawler thread is interrupted. 2{}", e.getMessage());
                        }
                    }
                    if (ret) {
                        break;
                    }
                }

            } catch (Throwable e) {
                if (e instanceof InterruptedException) {
                    logger.info(">>>>>>>>>>> xxl crawler thread is interrupted. {}", e.getMessage());
                } else if (e instanceof XxlCrawlerException) {
                    logger.info(">>>>>>>>>>> xxl crawler thread {}", e.getMessage());
                } else {
                    logger.error(e.getMessage(), e);
                }
            }

        }
    }



    /**
     * make page request
     *
     * @param link
     * @return PageRequest
     */
    private PageRequest makePageRequest(String link){
        String userAgent = crawler.getRunConf().getUserAgentList().size()>1
                ?crawler.getRunConf().getUserAgentList().get(new Random().nextInt(crawler.getRunConf().getUserAgentList().size()))
                :crawler.getRunConf().getUserAgentList().size()==1?crawler.getRunConf().getUserAgentList().get(0):null;
        Proxy proxy = null;
        if (crawler.getRunConf().getProxyMaker() != null) {
            proxy = crawler.getRunConf().getProxyMaker().make();
        }

        PageRequest pageRequest = new PageRequest();
        pageRequest.setUrl(link);
        pageRequest.setParamMap(crawler.getRunConf().getParamMap());
        pageRequest.setCookieMap(crawler.getRunConf().getCookieMap());
        pageRequest.setHeaderMap(crawler.getRunConf().getHeaderMap());
        pageRequest.setUserAgent(userAgent);
        pageRequest.setReferrer(crawler.getRunConf().getReferrer());
        pageRequest.setIfPost(crawler.getRunConf().isIfPost());
        pageRequest.setTimeoutMillis(crawler.getRunConf().getTimeoutMillis());
        pageRequest.setProxy(proxy);
        pageRequest.setValidateTLSCertificates(crawler.getRunConf().isValidateTLSCertificates());

        return pageRequest;
    }

    /**
     * process non page
     * @param pageRequest
     * @return boolean
     */
    private boolean processNonPage(PageRequest pageRequest){
        NonPageParser nonPageParser = (NonPageParser) crawler.getRunConf().getPageParser();

        String pagesource = JsoupUtil.loadPageSource(pageRequest);
        if (pagesource == null) {
            return false;
        }
        nonPageParser.parse(pageRequest.getUrl(), pagesource);
        return true;
    }

    /**
     * process page
     * @param pageRequest
     * @return boolean
     */
    private boolean processPage(PageRequest pageRequest) throws IllegalAccessException, InstantiationException {
        Document html = crawler.getRunConf().getPageLoader().load(pageRequest);

        if (html == null) {
            return false;
        }


        // ------- child link list (FIFO队列,广度优先) ----------
        if (crawler.getRunConf().isAllowSpread()) {     // limit child spread
            Set<String> links = JsoupUtil.findLinks(html);
            if (links != null && links.size() > 0) {
                for (String item : links) {
                    if (crawler.getRunConf().validWhiteUrl(item)) {      // limit unvalid-child spread
                        crawler.getRunData().addUrl(item);
                    }
                }
            }
        }

        // ------- pagevo ----------
        if (!crawler.getRunConf().validWhiteUrl(pageRequest.getUrl())) {     // limit unvalid-page parse, only allow spread child, finish here
            return true;
        }

        // pagevo class-field info
        Class pageVoClassType = Object.class;

        Type pageVoParserClass = crawler.getRunConf().getPageParser().getClass().getGenericSuperclass();
        if (pageVoParserClass instanceof ParameterizedType) {
            Type[] pageVoClassTypes = ((ParameterizedType)pageVoParserClass).getActualTypeArguments();
            pageVoClassType = (Class) pageVoClassTypes[0];
        }

        //经过修改----动态赋值
        Select select = crawler.getSelect();

        String pageVoCssQuery = "html";
        if (select.getLevel()>0&& select !=null){
            String pageSelect = select.getPageSelect();
            if (pageSelect!=null&&pageSelect!=""){
                pageVoCssQuery = pageSelect;
            }else {
                pageVoCssQuery = "html";
            }
        }else {
            PageSelect pageVoSelect = (PageSelect) pageVoClassType.getAnnotation(PageSelect.class);
            pageVoCssQuery = (pageVoSelect!=null && pageVoSelect.cssQuery()!=null && pageVoSelect.cssQuery().trim().length()>0)?pageVoSelect.cssQuery():"html";
        }
        //结束


        // pagevo document 2 object
        Elements pageVoElements = html.select(pageVoCssQuery);

        if (pageVoElements != null && pageVoElements.hasText()) {
            for (Element pageVoElement : pageVoElements) {

                Object pageVo = null;
                try {
                    pageVo = pageVoClassType.getDeclaredConstructor().newInstance();
                } catch (Exception e) {
                    e.printStackTrace();
                    logger.error(e.getMessage(), e);
                }

                Field[] fields = pageVoClassType.getDeclaredFields();
                if (fields!=null) {
                    for (Field field: fields) {
                        if (Modifier.isStatic(field.getModifiers())) {
                            continue;
                        }

                        //--------------------经过修改，动态赋值
                        // field origin value
                        String name = field.getName();
                        String cssQuery = null;
                        XxlCrawlerConf.SelectType selectType = null;
                        String selectVal = null;

                        if (select.getLevel()>0&& select !=null){
                            FieldSelect fieldSelects = select.getFieldSelectList().get(name);
                            if (select.getLevel()>1){
                                cssQuery = fieldSelects.getCssQuery();
                                selectType = fieldSelects.getSelectType();
                                selectVal = fieldSelects.getSelectVal();
                            }else {
                                PageFieldSelect fieldSelect = field.getAnnotation(PageFieldSelect.class);
                                cssQuery=fieldSelects.getCssQuery();
                                if (fieldSelect != null) {
                                    selectType = fieldSelect.selectType();
                                    selectVal = fieldSelect.selectVal();
                                }
                            }
                        }else {
                            PageFieldSelect fieldSelect = field.getAnnotation(PageFieldSelect.class);
                            if (fieldSelect != null) {
                                cssQuery = fieldSelect.cssQuery();
                                selectType = fieldSelect.selectType();
                                selectVal = fieldSelect.selectVal();
                            }
                        }

                        //------------结束

                        if (cssQuery==null || cssQuery.trim().length()==0) {
                            continue;
                        }

                        // field value
                        Object fieldValue = null;

                        if (field.getGenericType() instanceof ParameterizedType) {
                            ParameterizedType fieldGenericType = (ParameterizedType) field.getGenericType();
                            if (fieldGenericType.getRawType().equals(List.class)) {

                                //Type gtATA = fieldGenericType.getActualTypeArguments()[0];
                                Elements fieldElementList = pageVoElement.select(cssQuery);
                                if (fieldElementList!=null && fieldElementList.size()>0) {

                                    List<Object> fieldValueTmp = new ArrayList<Object>();
                                    for (Element fieldElement: fieldElementList) {

                                        String fieldElementOrigin = JsoupUtil.parseElement(fieldElement, selectType, selectVal);
                                        if (fieldElementOrigin==null || fieldElementOrigin.length()==0) {
                                            continue;
                                        }
                                        try {
                                            fieldValueTmp.add(FieldReflectionUtil.parseValue(field, fieldElementOrigin));
                                        } catch (Exception e) {
                                            logger.error(e.getMessage(), e);
                                        }
                                    }

                                    if (fieldValueTmp.size() > 0) {
                                        fieldValue = fieldValueTmp;
                                    }
                                }
                            }
                        } else {

                            Elements fieldElements = pageVoElement.select(cssQuery);
                            String fieldValueOrigin = null;
                            if (fieldElements!=null && fieldElements.size()>0) {
                                fieldValueOrigin = JsoupUtil.parseElement(fieldElements.get(0), selectType, selectVal);
                            }

                            if (fieldValueOrigin==null || fieldValueOrigin.length()==0) {
                                continue;
                            }

                            try {
                                fieldValue = FieldReflectionUtil.parseValue(field, fieldValueOrigin);
                            } catch (Exception e) {
                                logger.error(e.getMessage(), e);
                            }
                        }

                        if (fieldValue!=null) {
                            /*PropertyDescriptor pd = new PropertyDescriptor(field.getName(), pageVoClassType);
                            Method method = pd.getWriteMethod();
                            method.invoke(pageVo, fieldValue);*/

                            field.setAccessible(true);
                            field.set(pageVo, fieldValue);
                        }
                    }
                }

                // pagevo output
                pageVoElement.setBaseUri(pageRequest.getUrl());
                crawler.getRunConf().getPageParser().parse(html, pageVoElement, pageVo);
            }
        }

        return true;
    }

    /**
     * process page
     * @param pageRequest
     * @return boolean
     */

    private boolean processMapPage(PageRequest pageRequest) throws IllegalAccessException {
        Document html = crawler.getRunConf().getPageLoader().load(pageRequest);

        if (html == null) {
            return false;
        }

        // ------- child link list (FIFO队列,广度优先) ----------
        if (crawler.getRunConf().isAllowSpread()) {     // limit child spread
            Set<String> links = JsoupUtil.findLinks(html);
            if (links != null && links.size() > 0) {
                for (String item : links) {
                    if (crawler.getRunConf().validWhiteUrl(item)) {      // limit unvalid-child spread
                        crawler.getRunData().addUrl(item);
                    }
                }
            }
        }

        // ------- pagevo ----------
        if (!crawler.getRunConf().validWhiteUrl(pageRequest.getUrl())) {     // limit unvalid-page parse, only allow spread child, finish here
            return true;
        }


        //经过修改----动态赋值
        Select select = crawler.getSelect();

        String pageVoCssQuery = "html";
        String pageSelect = select.getPageSelect();
        if (pageSelect!=null&&pageSelect!=""){
            pageVoCssQuery = pageSelect;
        }
        //结束

        // pagevo document 2 object
        Elements pageVoElements = html.select(pageVoCssQuery);

        if (pageVoElements != null && pageVoElements.hasText()) {
            for (Element pageVoElement : pageVoElements) {

                Map<String,Object> pageVo =new HashMap<>();

                Map<String, FieldSelect> fieldSelectList = select.getFieldSelectList();
                if (fieldSelectList!=null) {
                    Set<Map.Entry<String, FieldSelect>> fields = fieldSelectList.entrySet();
                    for (Map.Entry<String, FieldSelect> field : fields) {
                        FieldSelect fieldSelect = field.getValue();
                        String name = fieldSelect.getParmname();
                        String cssQuery = fieldSelect.getCssQuery();
                        XxlCrawlerConf.SelectType selectType = fieldSelect.getSelectType();
                        String selectVal = fieldSelect.getSelectVal();
                        if (cssQuery==null || cssQuery.trim().length()==0) {
                            continue;
                        }
                        Object fieldValue = null;

                        if (fieldSelect.getParmClass().equals(List.class)) {
                            Elements fieldElementList = pageVoElement.select(cssQuery);
                            if (fieldElementList!=null && fieldElementList.size()>0) {

                                List<Object> fieldValueTmp = new ArrayList<Object>();
                                for (Element fieldElement: fieldElementList) {

                                    String fieldElementOrigin = JsoupUtil.parseElement(fieldElement, selectType, selectVal);
                                    if (fieldElementOrigin==null || fieldElementOrigin.length()==0) {
                                        continue;
                                    }
                                    try {
                                        fieldValueTmp.add(ModelUtil.parseValue(fieldSelect.getParmClass(),fieldSelect.getDatePattern(), fieldElementOrigin));
                                    } catch (Exception e) {
                                        logger.error(e.getMessage(), e);
                                    }
                                }

                                if (fieldValueTmp.size() > 0) {
                                    fieldValue = fieldValueTmp;
                                }
                            }
                        } else {
                            Elements fieldElements = pageVoElement.select(cssQuery);
                            String fieldValueOrigin = null;
                            if (fieldElements!=null && fieldElements.size()>0) {
                                fieldValueOrigin = JsoupUtil.parseElement(fieldElements.get(0), selectType, selectVal);
                            }

                            if (fieldValueOrigin==null || fieldValueOrigin.length()==0) {
                                continue;
                            }

                            try {
                                fieldValue = ModelUtil.parseValue(fieldSelect.getParmClass(),fieldSelect.getDatePattern(), fieldValueOrigin);
                            } catch (Exception e) {
                                logger.error(e.getMessage(), e);
                            }
                        }

                        pageVo.put(name,fieldValue);
                    }
                }

                // pagevo output
                pageVoElement.setBaseUri(pageRequest.getUrl());
                pageVo.put("baseUrl",pageRequest.getUrl());
                crawler.getRunConf().getPageParser().parse(html, pageVoElement, pageVo);
            }
        }

        return true;
    }
}