package com.seeuniv.project;

import com.seeuniv.project.domain.School;
import com.seeuniv.project.repository.SchoolRepository;
import com.seeuniv.project.repository.SubjectRepository;
import com.seeuniv.project.service.SchoolService;
import com.seeuniv.project.service.SchoolServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProjectApplication.class )
public class ProjectApplicationTests {


    @Autowired
    private SchoolServiceImpl schoolService;

    @Autowired
    private SubjectRepository subjectRepository;
    @Test
    public void contextLoads() {
    }

    @Test
    public void testSchoolRepository(){
        String [] schools = schoolService.getSchoolByPostion("jx");
        boolean isNullObj = (schools == null);
        System.out.println("江西里有多少个学校："+schools.length);
        System.out.println("江西里第一个大学为："+schools[0]);
    }

    @Test
    public void testSubjectRepository(){
        List<String> subjectTypeList = subjectRepository.findAllSubjectType();
        System.out.println("查询subject表的List集合大小为："+subjectTypeList.size()+subjectTypeList.get(0));
    }

}
