package com.seeuniv.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "subject")
/**
 * 暂时先设置这两个属性，在后面还有用户自己设置的专业介绍，
 * 一旦专业介绍在用户投票完成不可修改之后，在前端就不可以在修改，知道投票进行修改
 * 可以设置一个history介绍，显示最新的介绍，然后用户可以选择性查看相关介绍
 */
public class Subject {

    @Id
    @Column(name = "subject_name")
    private String subject_name;

    @Column(name = "subject_high_type")
    private String subject_high_type;

    @Column(name = "subject_mid_type")
    private String subject_mid_type;

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public String getSubject_high_type() {
        return subject_high_type;
    }

    public void setSubject_high_type(String subject_high_type) {
        this.subject_high_type = subject_high_type;
    }

    public String getSubject_mid_type() {
        return subject_mid_type;
    }

    public void setSubject_mid_type(String subject_mid_type) {
        this.subject_mid_type = subject_mid_type;
    }
}
