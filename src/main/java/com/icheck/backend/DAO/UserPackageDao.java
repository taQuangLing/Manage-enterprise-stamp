package com.icheck.backend.DAO;

import com.icheck.backend.entity.UserPackage;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.repositority.UserPackageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPackageDao {
    @Autowired
    private UserPackageRepo repo;
    public UserPackage getById(Long id){
        UserPackage userPackage = repo.findById(id).orElse(null);
        if (userPackage == null)throw new ApiException(ErrorMessage.UP_EXISTED);
        return userPackage;
    }
}
