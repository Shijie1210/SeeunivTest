package com.seeuniv.project.controller;

import com.seeuniv.project.domain.HighUser;
import com.seeuniv.project.repository.HighUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/high")
public class HighUserController {

    @Autowired
    private HighUserRepository highUserRepository;

    @GetMapping(value = "/login/{userName}")
    public ModelAndView canLogin(ModelAndView mv,
                                 HighUser highUser,
                                 HttpServletRequest request,
                                 @PathVariable String userName) {
        //显示判断能否通过该用户查询到相应的数据库数据

        boolean isNullObj = (highUserRepository.findByUserName(userName) == null);
        if (isNullObj != true) {
            highUser = highUserRepository.findByUserName(userName);
        }
        boolean isNotLogin = (request.getSession().getAttribute("highStatus") == null);
        request.getSession().setAttribute("user", highUser);
        request.getSession().setAttribute("userName", userName);
        request.getSession().setAttribute("role", "high");
        if (isNullObj == true || isNotLogin == true) {
            mv.setViewName("login");
        } else {

            mv.setViewName("StuLogin");
        }
        return mv;
    }
}
