package com.wyuxks.neteasecloud.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by jingbin on 2016/11/24.
 */

public class GankIoDayBean implements Serializable {

    public boolean error;
    public ResultsBean results;
    public List<String> category;

    public static class ResultsBean {
        /**
         * _id : 56cc6d23421aa95caa707a69
         * createdAt : 2015-08-06T07:15:52.65Z
         * desc : 类似Link Bubble的悬浮式操作设计
         * images : ["http://img.gank.io/2f0b6c5f-6de7-4ba3-94ad-98bf721ee447"]
         * source : web
         * publishedAt : 2015-08-07T03:57:48.45Z
         * type : Android
         * url : https://github.com/recruit-lifestyle/FloatingView
         * used : true
         * who : mthli
         */

        public List<ItemBean> Android;

        public List<ItemBean> iOS;

        public List<ItemBean> 前端;

        public List<ItemBean> App;

        public List<ItemBean> 休息视频;

        public List<ItemBean> 拓展资源;

        public List<ItemBean> 瞎推荐;

        public List<ItemBean> 福利;
    }


}
