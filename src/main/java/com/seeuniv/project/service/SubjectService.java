package com.seeuniv.project.service;

import org.springframework.stereotype.Service;

import java.util.List;


public interface SubjectService {
    public List<String> getAllSubjectType();

    public String[] getNameByType(String type);
}
