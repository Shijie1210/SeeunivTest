package com.seeuniv.project.repository;

import com.seeuniv.project.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject,String> {
    //自定义原生数据库查询语句

    /**
     * 利用原生数据查询语句查询所有的专业类型
     * @return
     */
    @Query(value = "select distinct subject_type from subject ",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    public List<String> findAllSubjectType();

    /**
     * 原生数据库查询所有专业名称
     * @return
     */
    @Query(value = "select distinct subject_name from subject",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    public String[] findAllSubjectName();

    /**
     * 通过类型查找用户名
     * @param type
     * @return
     */
    @Query(value = "select subject_name from subject where subject_type = ?1",nativeQuery = true)
    @Modifying(clearAutomatically = true)
    public String[] findAllNameBySubjectType(String type);
}
