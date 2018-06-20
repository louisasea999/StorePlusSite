package com.dxc.pai.model;

import java.util.Date;

public class FindAllfaces {
    private Integer id;

    private String imageid;

    private String facetoken;

    private String videopath;

    private String gender;

    private Integer age;

    private Object eyestatus;

    private Object emotion;

    private String ethnicity;

    private Double maleScore;

    private Double femaleScore;

    private Object skinstatus;

    private String freeinfo;

    private String facesetOuterid;

    private String facesetStatus;

    private String picname;

    private Date pictime;

    private Integer snapint;

    private Integer snapseq;

    private Object blur;

    private Object eyegaze;

    private Object facequality;

    private Object headpose;

    private Double mostlikeScore;

    private Object mouthstatus;

    private Object smile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageid() {
        return imageid;
    }

    public void setImageid(String imageid) {
        this.imageid = imageid == null ? null : imageid.trim();
    }

    public String getFacetoken() {
        return facetoken;
    }

    public void setFacetoken(String facetoken) {
        this.facetoken = facetoken == null ? null : facetoken.trim();
    }

    public String getVideopath() {
        return videopath;
    }

    public void setVideopath(String videopath) {
        this.videopath = videopath == null ? null : videopath.trim();
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender == null ? null : gender.trim();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Object getEyestatus() {
        return eyestatus;
    }

    public void setEyestatus(Object eyestatus) {
        this.eyestatus = eyestatus;
    }

    public Object getEmotion() {
        return emotion;
    }

    public void setEmotion(Object emotion) {
        this.emotion = emotion;
    }

    public String getEthnicity() {
        return ethnicity;
    }

    public void setEthnicity(String ethnicity) {
        this.ethnicity = ethnicity == null ? null : ethnicity.trim();
    }

    public Double getMaleScore() {
        return maleScore;
    }

    public void setMaleScore(Double maleScore) {
        this.maleScore = maleScore;
    }

    public Double getFemaleScore() {
        return femaleScore;
    }

    public void setFemaleScore(Double femaleScore) {
        this.femaleScore = femaleScore;
    }

    public Object getSkinstatus() {
        return skinstatus;
    }

    public void setSkinstatus(Object skinstatus) {
        this.skinstatus = skinstatus;
    }

    public String getFreeinfo() {
        return freeinfo;
    }

    public void setFreeinfo(String freeinfo) {
        this.freeinfo = freeinfo == null ? null : freeinfo.trim();
    }

    public String getFacesetOuterid() {
        return facesetOuterid;
    }

    public void setFacesetOuterid(String facesetOuterid) {
        this.facesetOuterid = facesetOuterid == null ? null : facesetOuterid.trim();
    }

    public String getFacesetStatus() {
        return facesetStatus;
    }

    public void setFacesetStatus(String facesetStatus) {
        this.facesetStatus = facesetStatus == null ? null : facesetStatus.trim();
    }

    public String getPicname() {
        return picname;
    }

    public void setPicname(String picname) {
        this.picname = picname == null ? null : picname.trim();
    }

    public Date getPictime() {
        return pictime;
    }

    public void setPictime(Date pictime) {
        this.pictime = pictime;
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

    public Object getBlur() {
        return blur;
    }

    public void setBlur(Object blur) {
        this.blur = blur;
    }

    public Object getEyegaze() {
        return eyegaze;
    }

    public void setEyegaze(Object eyegaze) {
        this.eyegaze = eyegaze;
    }

    public Object getFacequality() {
        return facequality;
    }

    public void setFacequality(Object facequality) {
        this.facequality = facequality;
    }

    public Object getHeadpose() {
        return headpose;
    }

    public void setHeadpose(Object headpose) {
        this.headpose = headpose;
    }

    public Double getMostlikeScore() {
        return mostlikeScore;
    }

    public void setMostlikeScore(Double mostlikeScore) {
        this.mostlikeScore = mostlikeScore;
    }

    public Object getMouthstatus() {
        return mouthstatus;
    }

    public void setMouthstatus(Object mouthstatus) {
        this.mouthstatus = mouthstatus;
    }

    public Object getSmile() {
        return smile;
    }

    public void setSmile(Object smile) {
        this.smile = smile;
    }
}