package com.seeuniv.project.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "school")
public class School {
    @Id
    @Column(name = "school_name")
    private String schoolName;
    @Column(name = "school_position")
    private String schoolPosition;
    @Column(name = "school_belongs")
    private String schoolBelongs;
    @Column(name = "school_type")
    private String schoolType;
    @Column(name = "school_level")
    private String schoolLevel;
    @Column(name = "school_feachers")
    private String schoolFeachers;
    @Column(name = "school_short_position")
    private String schoolShortPosition;

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

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }

    public String getSchoolLevel() {
        return schoolLevel;
    }

    public void setSchoolLevel(String schoolLevel) {
        this.schoolLevel = schoolLevel;
    }

    public String getSchoolFeachers() {
        return schoolFeachers;
    }

    public void setSchoolFeachers(String schoolFeachers) {
        this.schoolFeachers = schoolFeachers;
    }

    public String getSchoolShortPosition() {
        return schoolShortPosition;
    }

    public void setSchoolShortPosition(String schoolShortPosition) {
        this.schoolShortPosition = schoolShortPosition;
    }
}
