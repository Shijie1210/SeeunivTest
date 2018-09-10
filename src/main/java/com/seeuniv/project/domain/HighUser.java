package com.seeuniv.project.domain;

import org.omg.CORBA.portable.IDLEntity;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Blob;

@Table(name = "high_User_info")
@Entity
public class HighUser implements Serializable {


    private int userID;
    @Id
    private String userName;
    private String password;
    private String schoolName;
    private String subject;
    private String city;
    private String college;
    private String enrollmentDate;//入学年份
    private int schoolingLength;//学制
    private String QQ;
    private String email;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStuName() {
        return userName;
    }

    public void setStuName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(String enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public int getSchoolingLength() {
        return schoolingLength;
    }

    public void setSchoolingLength(int schoolingLength) {
        this.schoolingLength = schoolingLength;
    }

    public String getQQ() {
        return QQ;
    }

    public void setQQ(String QQ) {
        this.QQ = QQ;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return userName+"  "+password;
    }
}
