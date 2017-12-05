package com.wyuxks.neteasecloud.bean;

import java.util.List;

/**
 * Author : xks
 * Data : 2017/7/13 0013
 * Des :
 */

public class BannerBean {


    /**
     * data : [{"bannerid":65,"bannerurl":"http://218.17.157.212:9999/test/image/png/0df5cbd51470fa256cb484c69501b886.png","createtime":"2017-07-06 11:24:57.0","id":4,"type":1},{"bannerid":66,"bannerurl":"http://218.17.157.212:9999/test/image/jpeg/76f02ccc3f40d972abce0f11c8cab95e.jpg","createtime":"2017-07-06 11:24:43.0","id":3,"type":1},{"bannerid":61,"bannerurl":"http://218.17.157.212:9999/test/image/jpeg/9cf7863a720e409df63c91d6e1728263.jpg","createtime":"2017-07-06 11:23:27.0","id":1,"type":1}]
     * page : {"counts":3,"curPage":1,"firstResult":0,"lastResult":11,"orderBy":"","pageSize":10,"totalPage":1}
     * retCode : 0
     */

    public PageBean page;
    public int retCode;
    public String message;
    public List<DataBean> data;


    public static class PageBean {
         /**
         * counts : 3
         * curPage : 1
         * firstResult : 0
         * lastResult : 11
         * orderBy :
         * pageSize : 10
         * totalPage : 1
         */

       public int counts;
       public int curPage;
       public int firstResult;
       public int lastResult;
       public String orderBy;
       public int pageSize;
       public int totalPage;


    }

    public static class DataBean {
        /**
         * bannerid : 65
         * bannerurl : http://218.17.157.212:9999/test/image/png/0df5cbd51470fa256cb484c69501b886.png
         * createtime : 2017-07-06 11:24:57.0
         * id : 4
         * useid : 4
         * type : 1
         */

        public int bannerid;
        public String bannerurl;
        public String createtime;
        public int id;
        public int type;
        public int useid;
    }
}
