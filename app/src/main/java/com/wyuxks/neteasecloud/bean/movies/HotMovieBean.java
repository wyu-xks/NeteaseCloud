package com.wyuxks.neteasecloud.bean.movies;



import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jingbin on 2016/11/25.
 */

public class HotMovieBean implements Serializable {

    private int count;
    private int start;
    private int total;
    private String title;
    private ArrayList<SubjectsBean> subjects;

    public int getCount() {
        return count;
    }
    public int getStart() {
        return start;
    }
    public int getTotal() {
        return total;
    }
    public String getTitle() {
        return title;
    }
    public ArrayList<SubjectsBean> getSubjects() {
        return subjects;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSubjects(ArrayList<SubjectsBean> subjects) {
        this.subjects = subjects;
    }
}
