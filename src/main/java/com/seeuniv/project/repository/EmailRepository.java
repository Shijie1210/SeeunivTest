package com.seeuniv.project.repository;

import com.seeuniv.project.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepository extends JpaRepository<Email,String> {
    public Email findByEmail(String name);
    public Email findByUserName(String userName);
}
