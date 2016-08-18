package com.looper.ultimate.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/7/4.
 */
public class ClassifyClass extends BaseBean {

    private boolean status;

    private List<Galleryclass> tngou;

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<Galleryclass> getTngou() {
        return tngou;
    }

    public void setTngou(List<Galleryclass> tngou) {
        this.tngou = tngou;
    }


}
