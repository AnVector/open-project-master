package com.looper.ultimate.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class GalleryList extends BaseBean {

    private boolean status;
    private int total;//数据总数
    private List<Gallery> tngou;//数据列表

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Gallery> getTngou() {
        return tngou;
    }

    public void setTngou(List<Gallery> tngou) {
        this.tngou = tngou;
    }

}
