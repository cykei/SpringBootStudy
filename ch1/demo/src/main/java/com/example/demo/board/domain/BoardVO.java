package com.example.demo.board.domain;

import java.util.Date;

public class BoardVO {


    int bno;
    String title;
    String userID;
    String contents;
    String reg_dt;

    public int getBno() {
        return bno;
    }
    public void setBno(int bno) {
        this.bno = bno;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getContents() {
        return contents;
    }
    public void setContents(String contents) {
        this.contents = contents;
    }
    public String getReg_dt() {
        return reg_dt;
    }
    public void setReg_dt(String reg_dt) {
        this.reg_dt = reg_dt;
    }
}

