package com.seeuniv.project.controller;

import com.seeuniv.project.domain.Email;
import com.seeuniv.project.domain.HighUser;
import com.seeuniv.project.functionModule.CheckImg;
import com.seeuniv.project.repository.EmailRepository;
import com.seeuniv.project.repository.HighUserRepository;
import com.seeuniv.project.repository.MidUserRepository;
import com.seeuniv.project.service.HighUserServiceImpl;
import com.seeuniv.project.service.MidUserServiceImpl;
import com.seeuniv.project.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 控制主页上发出的请求
 */
@RestController
@RequestMapping("/")
public class IndexController {

    @Autowired
    private HighUserServiceImpl highUserServiceImpl;

    @Autowired
    private MidUserServiceImpl midUserServiceImpl;

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private HighUserRepository highUserRepository;

    @Autowired
    private MidUserRepository midUserRepository;

    @Autowired
    private SubjectServiceImpl subjectService;

    //显示主页
    @GetMapping(value = "/")
    public ModelAndView indexStart(ModelAndView modelAndView) {
        modelAndView.setViewName("index");
        return modelAndView;
    }

    //从主页跳转到这里的登陆页面
    @PostMapping(value = "/")
    public ModelAndView loginPage(ModelAndView modelAndView, Model model, HighUser highUser) {
        model.addAttribute(highUser);
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/login")
    public ModelAndView getLogin(ModelAndView mv) {
        mv.setViewName("login");
        return mv;
    }

    //用户登陆操作，从前端获取用户输入的数据

    /**
     * Ajax异步登陆，表单提交不能用form标签
     *
     * @param userName
     * @param password
     * @param role
     * @param checkImg
     * @param request
     * @return
     */
    @PostMapping(value = "/login")
    @ResponseBody
    public String canLogin(@RequestParam String userName,
                           @RequestParam String password,
                           @RequestParam String role,
                           @RequestParam String checkImg,
                           HttpServletRequest request) {
        String checkCode = (String) request.getSession().getAttribute("checkCode");
        boolean validUser = (emailRepository.findByUserName(userName) != null);
        if (validUser == true) {
            Email emailObj = emailRepository.findByUserName(userName);
            if (role.equals("highStu") && emailObj.getActived() == 0) {
                //大学生登陆
                boolean flag = highUserServiceImpl.isEffect(userName, password, checkCode, checkImg);
                //如果用户登陆成功的话，那么设置一个登陆状态成功标志位
                if (flag == true) {
                    request.getSession().setAttribute("highStatus", "ok");
                }
                return String.valueOf(flag);
            } else if (role.equals("midStu") && emailObj.getActived() == 0) {
                //高中生登陆
                boolean flag = midUserServiceImpl.isEffect(userName, password, checkCode, checkImg);
                if (flag == true) {
                    //如果用户登陆成功的话，那么设置一个登陆状态成功标志位
                    request.getSession().setAttribute("midStatus", "ok");
                }
                return String.valueOf(flag);
            } else {
                return "notActive";
            }
        } else {
            return "userNotFound";
        }
    }

    /**
     * 获取验证码，没有对验证码进行加密处理
     * login/getIma/{noAffence}：表示后面加一个后缀，为了区分每次请求，具体给的是日期
     *
     * @param request
     * @param response
     * @throws IOException
     */
    @GetMapping(value = "/login/getImg/{noAffence}")
    public void checkImg(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CheckImg.drawImg(request, response);
        System.out.println((String) request.getSession().getAttribute("checkCode") + "这个验证码session变量是在CheckImg里设置的，在IndexController里获取显示");

    }

    @GetMapping(value = "/regist")
    public ModelAndView registPage(ModelAndView mv, Model model) {
        model.addAttribute("subjectTypeList",subjectService.getAllSubjectType());
        model.addAttribute("enrollmentDate", highUserServiceImpl.enrollmentDateList());
        mv.setViewName("regist");
        return mv;
    }

    /**
     * 用户注册，需要在这里加MD5验证，同时对邮箱进行不区分大小写
     *
     * @param userName
     * @param password
     * @param email
     * @param QQ
     * @param city
     * @param school
     * @param subjectType
     * @param subject
     * @return
     */
    @RequestMapping(value = "/regist", method = RequestMethod.POST)
    @ResponseBody
    public String registUser(@RequestParam String userName,
                             @RequestParam String password,
                             @RequestParam String email,
                             @RequestParam String QQ,
                             @RequestParam String city,
                             @RequestParam String school,
                             @RequestParam String subjectType,
                             @RequestParam String subject,
                             @RequestParam String enrollmentDate,
                             @RequestParam String schoolingLength,
                             HttpServletRequest request) {
        //如果school等于0的话【在select里设置的】说明是高中生，高中生不需要那些学校信息
        boolean foundEmail = !(emailRepository.findByEmail(email) == null);
        boolean foundMidUser = !(midUserRepository.findByMidStuName(userName) == null);
        boolean foundHighUser = !(highUserRepository.findByUserName(userName) == null);

        if (school.equalsIgnoreCase("0")) {
            if(foundEmail != true){
                if(foundHighUser != true){
                    if(foundMidUser != true){
                        midUserServiceImpl.insertStu(userName, password, email, QQ);
                        request.getSession().setAttribute("userName", userName);
                        request.getSession().setAttribute("email", email);
                        return "success";
                    }else {
                        return "userExist";
                    }
                }else {
                    return "userExist";
                }
            }else {
                return "emailExist";
            }
        } else {
            if(foundEmail != true){
                if(foundHighUser != true){
                    if(foundMidUser != true){
                        highUserServiceImpl.insertStu(userName, password, email, QQ, city, school, subjectType, subject, enrollmentDate, Integer.parseInt(schoolingLength));
                        request.getSession().setAttribute("userName", userName);
                        request.getSession().setAttribute("email", email);
                        return "success";
                    }else {
                        return "userExist";
                    }
                }else {
                    return "userExist";
                }
            }else {
                return "emailExist";
            }
        }
    }

    /**
     * 用户退出功能
     *
     * @param modelAndView
     * @param request
     * @return
     */
    @GetMapping(value = "/exit")
    public ModelAndView exitApp(ModelAndView modelAndView,
                                HttpServletRequest request) {

        request.getSession().invalidate();  //清除session中所有信息
        modelAndView.setViewName("index");
        return modelAndView;

    }
}
