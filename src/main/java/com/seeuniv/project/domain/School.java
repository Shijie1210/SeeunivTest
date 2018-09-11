package com.seeuniv.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "school")
public class School {

    @Column(name = "school_name")
    private String schoolName;

    @Column(name = "school_position")
    private String schoolPosition;

    @Column(name = "school_belongs")
    private String schoolBelongs;

    @Column(name = "school_level")
    private String schoolLevel;

    @Id
    @Column(name = "school_num")
    private String schoolNum;
    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSchoolPosition() {
        return schoolPosition;
    }

    public void setSchoolPosition(String schoolPosition) {
        this.schoolPosition = schoolPosition;
    }

    public String getSchoolBelongs() {
        return schoolBelongs;
    }

    public void setSchoolBelongs(String schoolBelongs) {
        this.schoolBelongs = schoolBelongs;
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getSchoolNum() {
        return schoolNum;
    }

    public void setSchoolNum(String schoolNum) {
        this.schoolNum = schoolNum;
    }
}
