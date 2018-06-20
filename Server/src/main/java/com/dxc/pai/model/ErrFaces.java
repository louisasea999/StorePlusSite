package com.dxc.pai.model;

import java.util.Date;

public class ErrFaces {
    private Integer id;

    private String picpath;

    private String errmsg;

    private Date chstamp;

    private String fixstatus;

    private Integer snapint;

    private Integer snapseq;

    private String fixmsg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicpath() {
        return picpath;
    }

    public void setPicpath(String picpath) {
        this.picpath = picpath == null ? null : picpath.trim();
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg == null ? null : errmsg.trim();
    }

    public Date getChstamp() {
        return chstamp;
    }

    public void setChstamp(Date chstamp) {
        this.chstamp = chstamp;
    }

    public String getFixstatus() {
        return fixstatus;
    }

    public void setFixstatus(String fixstatus) {
        this.fixstatus = fixstatus == null ? null : fixstatus.trim();
    }

    public Integer getSnapint() {
        return snapint;
    }

    public void setSnapint(Integer snapint) {
        this.snapint = snapint;
    }

    public Integer getSnapseq() {
        return snapseq;
    }

    public void setSnapseq(Integer snapseq) {
        this.snapseq = snapseq;
    }

    public String getFixmsg() {
        return fixmsg;
    }

    public void setFixmsg(String fixmsg) {
        this.fixmsg = fixmsg == null ? null : fixmsg.trim();
    }
}