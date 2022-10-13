package com.icheck.backend.repositority;

import com.icheck.backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    public User findByEmail(String email);

    public User findByPhone(String phone);

    public User findByTaxCode(String taxCode);
}
