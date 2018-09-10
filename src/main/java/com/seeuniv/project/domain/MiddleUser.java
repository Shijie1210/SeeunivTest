package com.seeuniv.project.domain;

import javax.persistence.*;

@Table(name = "mid_user_info")
@Entity
public class MiddleUser {


    private int id;
    @Id
    private String midStuName;
    private String midStuPassword;
    private String email;
    private String QQ;

    public String getStuName() {
        return midStuName;
    }

    public void setStuName(String midStuName) {
        this.midStuName = midStuName;
    }

    public String getMidStuPassword() {
        return midStuPassword;
    }

    public void setMidStuPassword(String midStuPassword) {
        this.midStuPassword = midStuPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }
}
