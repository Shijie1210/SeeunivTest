package com.seeuniv.project.controller;

import com.seeuniv.project.service.SchoolServiceImpl;
import com.seeuniv.project.service.SubjectServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/regist")
public class RegistController {

    @Autowired
    private SchoolServiceImpl schoolService;

    @Autowired
    private SubjectServiceImpl subjectService;

    @GetMapping(value = "/city/{position}")
    public String[] getSchoolList(@PathVariable String position){
        return schoolService.getSchoolByPostion(position);
    }

    @GetMapping(value = "/toptype/{type}")
    public String[] getSecondSubjectList(@PathVariable String type){
//        System.out.println(topType);
        return subjectService.getSecondSubjectType(type);
    }

    @GetMapping(value = "/secondtype/{secondType}")
    public String[] getSubjectList(@PathVariable String secondType){
        return subjectService.getSubjectName(secondType);
    }
}
