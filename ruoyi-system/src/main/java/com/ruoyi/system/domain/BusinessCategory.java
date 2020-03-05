package com.ruoyi.system.domain;

public class BusinessCategory {
    private Integer id;

    private String oneCategoryName;

    private String oneCategoryCode;

    private String twoCategoryName;

    private String twoCategoryCode;

    private String threeCategoryName;

    private String threeCategoryCode;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOneCategoryName() {
        return oneCategoryName;
    }

    public void setOneCategoryName(String oneCategoryName) {
        this.oneCategoryName = oneCategoryName == null ? null : oneCategoryName.trim();
    }

    public String getOneCategoryCode() {
        return oneCategoryCode;
    }

    public void setOneCategoryCode(String oneCategoryCode) {
        this.oneCategoryCode = oneCategoryCode == null ? null : oneCategoryCode.trim();
    }

    public String getTwoCategoryName() {
        return twoCategoryName;
    }

    public void setTwoCategoryName(String twoCategoryName) {
        this.twoCategoryName = twoCategoryName == null ? null : twoCategoryName.trim();
    }

    public String getTwoCategoryCode() {
        return twoCategoryCode;
    }

    public void setTwoCategoryCode(String twoCategoryCode) {
        this.twoCategoryCode = twoCategoryCode == null ? null : twoCategoryCode.trim();
    }

    public String getThreeCategoryName() {
        return threeCategoryName;
    }

    public void setThreeCategoryName(String threeCategoryName) {
        this.threeCategoryName = threeCategoryName == null ? null : threeCategoryName.trim();
    }

    public String getThreeCategoryCode() {
        return threeCategoryCode;
    }

    public void setThreeCategoryCode(String threeCategoryCode) {
        this.threeCategoryCode = threeCategoryCode == null ? null : threeCategoryCode.trim();
    }
}