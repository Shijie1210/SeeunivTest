package com.seeuniv.project.controller;

import com.seeuniv.project.Util.RandomCheckCode;
import com.seeuniv.project.domain.Email;
import com.seeuniv.project.repository.EmailRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/mail")
public class SendMailController {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailRepository emailRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping(value = "/sendemail")
    public ModelAndView sendEmail(ModelAndView mv, HttpServletRequest request) {
        //用户注册完了之后就往这跳
        //判断用户是否完成了激活
        String userName = (String) request.getSession().getAttribute("userName");
        String email = (String) request.getSession().getAttribute("email");
        //在这需要对用户进行有效性判断
        final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage);
        try {
            message.setFrom("676568045@qq.com");
            message.setTo(email);
            message.setSubject("知寻网注册邮件");
            //设置邮件正文
            message.setText(sendMailContent(userName, email), true);
            this.mailSender.send(mimeMessage);
            logger.info("邮件发送成功");
            mv.setViewName("activeUser");
            return mv;
        } catch (MessagingException e) {
            logger.error("邮件发送异常" + e);
            mv.setViewName("failCheck");
            return mv;
        }
    }

    public String sendMailContent(String userName, String email) {
        StringBuffer sb = new StringBuffer();
        String activeCode = RandomCheckCode.checkCode();
        //设置邮件内容时就更新数据库
        updateEmailBefore(userName, email, activeCode);
        String helloHead = "<h2>" + userName + "您好：</h2>";
        sb.append(helloHead)
                .append("<h2>感谢注册知寻网<h2><br/>")
                .append("<h1>验证码为：" + activeCode + "</h1>")
                .append("如果不是您创建的账号请忽略此封邮件");
        return sb.toString();
    }

    /**
     * 用户激活之前设更新用户
     *
     * @param userName
     * @param email
     * @param activeCode
     */
    public void updateEmailBefore(String userName, String email, String activeCode) {
        Email newEmail = new Email();
        newEmail.setUserName(userName);
        newEmail.setEmail(email);
        newEmail.setActiveCode(activeCode);
        newEmail.setActived(1);
        emailRepository.save(newEmail);
    }

    public void updateEmailAfter(String userName, Email emailObjBefore) {
        Email saveEmail = new Email();
        saveEmail.setEmail(emailObjBefore.getEmail());
        saveEmail.setUserName(emailObjBefore.getUserName());
        saveEmail.setActiveCode(emailObjBefore.getActiveCode());
        saveEmail.setActived(0);
        emailRepository.save(saveEmail);
    }

    /**
     * 这个方法写的是真的蠢，怎么用JPA进行更新操作？
     */
    @PostMapping(value = "/checkEmail")
    public String checkEmail(HttpServletRequest request,
                             @RequestParam String code) {
        String userName = (String) request.getSession().getAttribute("userName");
        //通过userName查找找到的话，进行对比actived码值
        boolean flag = (emailRepository.findByUserName(userName) != null);
        if (flag == true) {
            Email emailObjBefore = emailRepository.findByUserName(userName);
            if (emailObjBefore.getActiveCode().equalsIgnoreCase(code)) {
                updateEmailAfter(userName, emailObjBefore);
                return "success";
            } else {
                return "fail";
            }
        } else {
            return "fail";
        }
    }
}
