package com.dxc.pai.model;

import java.util.Date;

public class OrderTable {
    private String id;

    private Date opentime;

    private String tablecode;

    private String fooddetails;

    private Integer findAllfacesId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public Date getOpentime() {
        return opentime;
    }

    public void setOpentime(Date opentime) {
        this.opentime = opentime;
    }

    public String getTablecode() {
        return tablecode;
    }

    public void setTablecode(String tablecode) {
        this.tablecode = tablecode == null ? null : tablecode.trim();
    }

    public String getFooddetails() {
        return fooddetails;
    }

    public void setFooddetails(String fooddetails) {
        this.fooddetails = fooddetails == null ? null : fooddetails.trim();
    }

    public Integer getFindAllfacesId() {
        return findAllfacesId;
    }

    public void setFindAllfacesId(Integer findAllfacesId) {
        this.findAllfacesId = findAllfacesId;
    }
}