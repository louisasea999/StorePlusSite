package com.dxc.pai.model;

import java.util.Date;

public class Foodcomb {
    private Integer id;

    private String comb;

    private Integer count;

    private Date createtime = new Date();

    private Date updatetime = new Date();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComb() {
        return comb;
    }

    public void setComb(String comb) {
        this.comb = comb == null ? null : comb.trim();
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}