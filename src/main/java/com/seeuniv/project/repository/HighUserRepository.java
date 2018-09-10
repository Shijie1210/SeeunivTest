package com.seeuniv.project.repository;

import com.seeuniv.project.domain.HighUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighUserRepository extends JpaRepository<HighUser,String> {
    public HighUser findByUserName(String userName);
}
