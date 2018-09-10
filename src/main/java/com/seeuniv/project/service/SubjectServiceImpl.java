package com.seeuniv.project.service;

import com.seeuniv.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Override
    public List<String> getAllSubjectType(){
        List<String> typeList = subjectRepository.findAllSubjectType();
        return typeList;
    }

    @Override
    public String[] getNameByType(String type) {
        String []nameList = subjectRepository.findAllNameBySubjectType(type);
        return nameList;
    }


}
