package com.seeuniv.project.service;

import com.seeuniv.project.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    public String[] getSchoolByPostion(String position) {
        String []schoolsName = schoolRepository.findBySchoolPosition(position);
        return schoolsName;
    }
}
