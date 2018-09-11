package com.seeuniv.project.service;

public interface SubjectService {
    public String[] getTopSubjectType();

    public String[] getSecondSubjectType(String topType);

    public String[] getSubjectName(String secondType);
}
