package com.seeuniv.project.service;

import com.seeuniv.project.domain.Email;
import com.seeuniv.project.domain.MiddleUser;
import com.seeuniv.project.repository.EmailRepository;
import com.seeuniv.project.repository.MidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MidUserServiceImpl implements UserService {

    @Autowired
    private MidUserRepository midUserRepository;

    @Autowired
    private EmailRepository emailRepository;

    public void insertStu(   String userName,
                             String password,
                             String email,
                             String QQ     ) {
        MiddleUser middleUser = new MiddleUser();
        Email emailObj = new Email();
        middleUser.setStuName(userName);
        middleUser.setMidStuPassword(password);
        middleUser.setEmail(email);
        middleUser.setQQ(QQ);
        midUserRepository.save(middleUser);
        emailObj.setEmail(email);
        emailObj.setUserName(userName);
        emailRepository.save(emailObj);
    }

    /**
     * 判断高中用户是否可以登陆
     *
     * @param checkCode
     * @param userCode
     * @return
     */
    @Override
    public boolean isEffect(String userName,
                            String password,
                            String checkCode,
                            String userCode) {
        MiddleUser midUserFromSql = midUserRepository.findByMidStuName(userName);
        boolean flag = (midUserFromSql == null);
        //如果查询数据库之后得到结果为null的话，直接返回false。防止空指针异常
        if (flag == false) {
            if (midUserFromSql.getMidStuPassword().equals(password) && checkCode.equalsIgnoreCase(userCode)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
