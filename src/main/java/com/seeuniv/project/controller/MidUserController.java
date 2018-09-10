package com.seeuniv.project.controller;

import com.seeuniv.project.domain.MiddleUser;
import com.seeuniv.project.repository.MidUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 高中生发送的请求处理类
 */

@RestController
@RequestMapping("/mid")
public class MidUserController {

    @Autowired
    private MidUserRepository midUserRepository;

    @GetMapping(value = "/login/{userName}")
    public ModelAndView canLogin(ModelAndView mv,
                                 MiddleUser middleUser,
                                 HttpServletRequest request,
                                 @PathVariable String userName) {
        //显示判断能否通过该用户查询到相应的数据库数据
        //再进一次行登陆验证吧
        boolean isNullObj = (midUserRepository.findByMidStuName(userName) == null);
        if (isNullObj != true) {
            middleUser = midUserRepository.findByMidStuName(userName);
        }
        boolean isNotLogin = (request.getSession().getAttribute("midStatus") == null);
        request.getSession().setAttribute("user", middleUser);
        request.getSession().setAttribute("userName", userName);
        request.getSession().setAttribute("role", "mid");
        if (isNullObj == true || isNotLogin == true) {
            mv.setViewName("login");
        } else {

            mv.setViewName("StuLogin");
        }
        return mv;
    }
}
