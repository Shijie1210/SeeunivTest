package com.seeuniv.project.controller;

import com.seeuniv.project.service.SchoolService;
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
        String []schoolNames = schoolService.getSchoolByPostion(position);
        return schoolNames;
    }

    @GetMapping(value = "/type/{type}")
    public String[] getSubjectList(@PathVariable String type){
        System.out.println(type);
        String [] subjects = subjectService.getNameByType(type);
        return subjects;
    }
}
