package com.icheck.backend.repositority;

import com.icheck.backend.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StampRepo extends JpaRepository<Stamp, Long>{

}
