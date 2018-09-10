package com.seeuniv.project.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Email {
    @Id
    private String email;
    private Integer actived = 1;    //1 代表未激活，0代表激活
    private String activeCode;
    private String userName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getActived() {
        return actived;
    }

    public void setActived(Integer actived) {
        this.actived = actived;
    }

    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
