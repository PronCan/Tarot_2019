package com.proncan.tarot;

import java.io.Serializable;

public class AllData implements Serializable {
    private String cardNo;
    private String cardName;
    private String cardEngName;
    private String keyword;
    private String keyword_rev;
    private String desc;    // 카드에 대한 설명
    private String comment; // 해설
    private String comment_rev; // 해설 역방향
    private String positive;    // 정방향긍정 -> 1긍정/0부정
    private String positive_rev;    // 역방향긍정

    public AllData(String cardNo, String cardName, String cardEngName, String keyword, String keyword_rev, String desc, String comment, String comment_rev, String positive, String positive_rev) {
        this.cardNo = cardNo;
        this.cardName = cardName;
        this.cardEngName = cardEngName;
        this.keyword = keyword;
        this.keyword_rev = keyword_rev;
        this.desc = desc;
        this.comment = comment;
        this.comment_rev = comment_rev;
        this.positive = positive;
        this.positive_rev = positive_rev;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public String getCardEngName() {
        return cardEngName;
    }

    public void setCardEngName(String cardEngName) {
        this.cardEngName = cardEngName;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getKeyword_rev() {
        return keyword_rev;
    }

    public void setKeyword_rev(String keyword_rev) {
        this.keyword_rev = keyword_rev;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment_rev() {
        return comment_rev;
    }

    public void setComment_rev(String comment_rev) {
        this.comment_rev = comment_rev;
    }

    public String getPositive() {
        return positive;
    }

    public void setPositive(String positive) {
        this.positive = positive;
    }

    public String getPositive_rev() {
        return positive_rev;
    }

    public void setPositive_rev(String positive_rev) {
        this.positive_rev = positive_rev;
    }
}