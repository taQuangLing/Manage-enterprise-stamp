package com.icheck.backend.DAO;

import com.icheck.backend.entity.Admin;
import com.icheck.backend.repositority.AdminRepositority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepositority repo;

    public Admin getById(Long id){
        return repo.findById(id).get();
    }


}
