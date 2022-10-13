package com.icheck.backend.DAO;

import com.icheck.backend.entity.User;
import com.icheck.backend.exception.ApiException;
import com.icheck.backend.exception.ErrorMessage;
import com.icheck.backend.repositority.UserRepo;
import com.sun.mail.imap.protocol.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDao {
    @Autowired
    private UserRepo repo;
    public User getById(Long id){
        User user = repo.findById(id).orElse(null);
        if (user == null)throw new ApiException(ErrorMessage.ID_EXISTED);
        return user;

    }
}
