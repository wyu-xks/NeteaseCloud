package com.wyuxks.neteasecloud.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Author : xks
 * Data : 2017/12/5 0005
 * Des :
 */

public class EveryDayItemBean {
    // 存储单独设置的值，用来显示title
    public String title;
    public List<ItemBean> contentLists = new ArrayList<>();

    public EveryDayItemBean(String title) {
        this.title = title;
    }

    public EveryDayItemBean(String title, List<ItemBean> contentLists) {
        this.title = title;
        this.contentLists = contentLists;
    }
}
