package com.dxc.pai.model;

public class AllFacesets {
    private Integer id;

    private String facesetToken;

    private Object faceTokens;

    private Integer faceCount;

    private String outerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFacesetToken() {
        return facesetToken;
    }

    public void setFacesetToken(String facesetToken) {
        this.facesetToken = facesetToken == null ? null : facesetToken.trim();
    }

    public Object getFaceTokens() {
        return faceTokens;
    }

    public void setFaceTokens(Object faceTokens) {
        this.faceTokens = faceTokens;
    }

    public Integer getFaceCount() {
        return faceCount;
    }

    public void setFaceCount(Integer faceCount) {
        this.faceCount = faceCount;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId == null ? null : outerId.trim();
    }
}