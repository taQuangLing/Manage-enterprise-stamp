package com.icheck.backend.repositority;

import com.icheck.backend.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositority extends JpaRepository<Admin, Long> {
    public Admin findByUserName(String username);
}
