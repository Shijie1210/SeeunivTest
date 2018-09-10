package com.seeuniv.project.service;

import com.seeuniv.project.domain.Email;
import com.seeuniv.project.domain.HighUser;
import com.seeuniv.project.repository.EmailRepository;
import com.seeuniv.project.repository.HighUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@Service
@Transactional
public class HighUserServiceImpl implements UserService{

    private static final int MAXENROLLMENTDATE = 2019;//设置列表可用的最大年份,当前年份加 1
    @Autowired
    private HighUserRepository highUserRepository;

    @Autowired
    private EmailRepository emailRepository;

    /**
     * 判断用户登陆的有效性
     *
     * @return
     */
    @Override
    public boolean isEffect(String userName,
                            String password,
                            String checkCode,
                            String userCode) {
        HighUser highUserFromSql = highUserRepository.findByUserName(userName);
        boolean flag = true;
        //判断查询出来的结果是否为null
        flag = (highUserFromSql == null);
        //System.out.println(flag);
        //如果查询数据库之后得到结果为null的话，直接返回false。防止空指针异常
        if (flag == false) {
            if (highUserFromSql.getPassword().equals(password) && checkCode.equalsIgnoreCase(userCode)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     * 将入学年份范围写入集合中
     *
     * @return
     */
    public ArrayList<String> enrollmentDateList() {
        ArrayList<String> edl = new ArrayList<>();
        for (int i = 2014; i < MAXENROLLMENTDATE; i++) {
            edl.add(Integer.toString(i));
        }
        return edl;
    }

    /**
     * 插入一个大学生
     *
     * @param userName
     * @param password
     * @param email
     * @param QQ
     * @param city
     * @param school
     * @param college
     * @param subject
     * @param enrollmentDate
     * @param schoolingLength
     */
    public boolean insertStu(String userName,
                             String password,
                             String email,
                             String QQ,
                             String city,
                             String school,
                             String college,
                             String subject,
                             String enrollmentDate,
                             int schoolingLength  ) {
        if (emailRepository.findByEmail(email) != null) {
            return false;
        } else {
            HighUser highUser = new HighUser();
            Email emailObj = new Email();
            highUser.setStuName(userName);
            highUser.setPassword(password);
            highUser.setEmail(email);
            highUser.setQQ(QQ);
            highUser.setCity(city);
            highUser.setSchoolName(school);
            highUser.setCollege(college);
            highUser.setSubject(subject);
            highUser.setEnrollmentDate(enrollmentDate);
            highUser.setSchoolingLength(schoolingLength);
            highUserRepository.save(highUser);
            emailObj.setEmail(email);
            emailObj.setUserName(userName);
            emailRepository.save(emailObj);
            return true;
        }
    }
}
