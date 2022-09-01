package com.proncan.tarot;

import java.io.Serializable;

public class Data implements Serializable {
    private String cardNo;
    private String cardName;
    private String cardEngName;
    private String keyword;
    private String comment1;
    private String comment2;
    private String comment3;
    private String comment4;
    private String comment5;
    private String comment1_rev;
    private String comment2_rev;
    private String comment3_rev;
    private String comment4_rev;
    private String comment5_rev;

    public Data(String cardNo, String cardName, String cardEngName, String keyword, String comment1, String comment2, String comment3, String comment4, String comment5, String comment1_rev, String comment2_rev, String comment3_rev, String comment4_rev, String comment5_rev) {
        this.cardNo = cardNo;
        this.cardName = cardName;
        this.cardEngName = cardEngName;
        this.keyword = keyword;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.comment3 = comment3;
        this.comment4 = comment4;
        this.comment5 = comment5;
        this.comment1_rev = comment1_rev;
        this.comment2_rev = comment2_rev;
        this.comment3_rev = comment3_rev;
        this.comment4_rev = comment4_rev;
        this.comment5_rev = comment5_rev;
    }

    public Data() {

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

    public String getComment1() {
        return comment1;
    }

    public void setComment1(String comment1) {
        this.comment1 = comment1;
    }

    public String getComment2() {
        return comment2;
    }

    public void setComment2(String comment2) {
        this.comment2 = comment2;
    }

    public String getComment3() {
        return comment3;
    }

    public void setComment3(String comment3) {
        this.comment3 = comment3;
    }

    public String getComment4() {
        return comment4;
    }

    public void setComment4(String comment4) {
        this.comment4 = comment4;
    }

    public String getComment5() {
        return comment5;
    }

    public void setComment5(String comment5) {
        this.comment5 = comment5;
    }

    public String getComment1_rev() {
        return comment1_rev;
    }

    public void setComment1_rev(String comment1_rev) {
        this.comment1_rev = comment1_rev;
    }

    public String getComment2_rev() {
        return comment2_rev;
    }

    public void setComment2_rev(String comment2_rev) {
        this.comment2_rev = comment2_rev;
    }

    public String getComment3_rev() {
        return comment3_rev;
    }

    public void setComment3_rev(String comment3_rev) {
        this.comment3_rev = comment3_rev;
    }

    public String getComment4_rev() {
        return comment4_rev;
    }

    public void setComment4_rev(String comment4_rev) {
        this.comment4_rev = comment4_rev;
    }

    public String getComment5_rev() {
        return comment5_rev;
    }

    public void setComment5_rev(String comment5_rev) {
        this.comment5_rev = comment5_rev;
    }
}
