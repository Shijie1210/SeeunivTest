package com.seeuniv.project.repository;

import com.seeuniv.project.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface SchoolRepository extends JpaRepository<School , String> {
    public School findBySchoolName(String schoolName);

    /**
     * 返回一个从school表里查询到的school集合,不确定参数进行查找，格式为在需要查找的位置设置为？后面再跟参数位置，1开始
     * @param position
     * @return
     */
    @Query(value = "select school_name from school where school_position=?1",nativeQuery = true)
    @Modifying
    public String[] findBySchoolPosition(String position);

    @Query(value = "select distinct school_position from school",nativeQuery = true)
    @Modifying
    public String[] findAllPostion();
}
