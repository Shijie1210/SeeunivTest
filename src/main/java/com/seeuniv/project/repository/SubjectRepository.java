package com.seeuniv.project.repository;

import com.seeuniv.project.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,String> {

    /**
     * 直接查找一级专业名称
     * @return
     */
    @Query(value = "select distinct subject_high_type from subject",nativeQuery = true)
    @Modifying
    public String[] findTopSubjectType();

    /**
     * 通过一级专业名称查找二级专业名称
     * @param topType
     * @return
     */
    @Query(value = "select distinct subject_mid_type from subject where subject_high_type = ?1",nativeQuery = true)
    @Modifying
    public String[] findSecondSubjectType(String topType);

    /**
     * 通过二级专业名称查找最终的专业名称
     * @param secondType
     * @return
     */
    @Query(value = "select distinct subject_name from subject where subject_mid_type =?1",nativeQuery = true)
    @Modifying
    public String[] findSubjectName(String secondType);

}
