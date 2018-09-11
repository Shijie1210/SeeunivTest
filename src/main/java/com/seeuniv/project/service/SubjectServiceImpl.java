package com.seeuniv.project.service;

import com.seeuniv.project.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;


    /**
     * 获取一级专业类型
     * @return
     */
    @Override
    public String[] getTopSubjectType() {
        return subjectRepository.findTopSubjectType();
    }

    /**
     * 通过一级专业类型查找二级专业类型
     * @param topType   一级专业类型
     * @return
     */
    @Override
    public String[] getSecondSubjectType(String topType) {
        return subjectRepository.findSecondSubjectType(topType);
    }

    /**
     * 通过二级专业类型查找专业名称
     * @param secondType    二级专业名称
     * @return
     */
    @Override
    public String[] getSubjectName(String secondType) {
        return subjectRepository.findSubjectName(secondType);
    }
}
