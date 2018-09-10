package com.seeuniv.project.repository;

import com.seeuniv.project.domain.MiddleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MidUserRepository extends JpaRepository<MiddleUser,String> {
    public MiddleUser findByMidStuName(String name);
}
