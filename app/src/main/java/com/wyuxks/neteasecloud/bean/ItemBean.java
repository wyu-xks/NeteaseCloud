package com.wyuxks.neteasecloud.bean;


import java.io.Serializable;
import java.util.List;

/**
 * Created by jingbin on 2016/11/30.
 * 首页item bean
 */

public class ItemBean implements Serializable {

    public String _id;
    public String createdAt;
    public String desc;
    public String publishedAt;
    public String type;
    public String url;
    public boolean used;
    public String who;

    public String source;
    public List<String> images;


}
