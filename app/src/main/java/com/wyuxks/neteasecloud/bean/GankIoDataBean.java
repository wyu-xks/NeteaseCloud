package com.wyuxks.neteasecloud.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by jingbin on 2016/11/24.
 */

public class GankIoDataBean implements Serializable {

    public boolean error;
    /**
     * _id : 5832662b421aa929b0f34e99
     * createdAt : 2016-11-21T11:12:43.567Z
     * desc :  深入Android渲染机制
     * publishedAt : 2016-11-24T11:40:53.615Z
     * source : web
     * type : Android
     * url : http://blog.csdn.net/ccj659/article/details/53219288
     * used : true
     * who : Chauncey
     */

    public List<ResultBean> results;

    public static class ResultBean implements Serializable {

        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
        public List<String> images;


        @Override
        public String toString() {
            return "ResultsBean{" +
                    "who='" + who + '\'' +
                    ", used=" + used +
                    ", url='" + url + '\'' +
                    ", type='" + type + '\'' +
                    ", source='" + source + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", _id='" + _id + '\'' +
                    '}';
        }

    }


    @Override
    public String toString() {
        return "GankIoDataBean{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
