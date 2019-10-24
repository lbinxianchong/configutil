package com.lbin.util.apiUtil;

import com.flannep.picacomic.api.PicaHeader;
import com.flannep.picacomic.api.api.*;
import com.flannep.picacomic.api.exceptions.PicaException;
import com.flannep.picacomic.api.others.pages.PageInfo;
import com.flannep.picacomic.api.results.PicaLoginResult;
import com.flannep.picacomic.api.results.announcement.Announcement;
import com.flannep.picacomic.api.results.announcement.PicaAnnouncementResult;

public class PicApi {

    private String token;

    private PicaHeader header;

    private PicaBookApi bookApi;
    private PicaSearchApi searchApi;
    private PicaUserApi userApi;
    private PicaIndexApi indexApi;

    public PicApi() {
    }

    public PicApi(String username, String password) {
        login(username, password);
        this.header = new PicaHeader();
        //设定身份验证字段 token为上一章用户登陆所获得的字符串
        header.setAuthorization(token);
        this.bookApi = new PicaBookApi(header);
        this.searchApi = new PicaSearchApi(header);
        this.userApi = new PicaUserApi(header);
        this.indexApi = new PicaIndexApi(header);
    }

    private void login(String username, String password) {
        try {
            //构建登陆api
            PicaLoginApi lapi = new PicaLoginApi(username, password);
            //获取服务端返回的登陆结果
            PicaLoginResult loginResult = lapi.login();
            this.token = loginResult.getToken();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 公告
     */
    public void page() {
        //获取指定页码的公告
        PicaAnnouncementResult annoResult = null;
        try {
            annoResult = indexApi.getAnnouncement(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //获取公告页码信息
        PageInfo pageInfo = annoResult.getPageInfo();
        //获取当前页码
        pageInfo.getPage();
        //获取总页码
        pageInfo.getPages();
        //遍历每条公告
        for (Announcement announcement : annoResult.getAnnouncements()) {
            announcement.getTitle();
            announcement.getContent();
        }
    }


}
