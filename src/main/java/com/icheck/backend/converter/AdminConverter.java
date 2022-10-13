package com.icheck.backend.converter;

import com.icheck.backend.entity.Admin;
import com.icheck.backend.security.AdminAccount;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class AdminConverter {
    public AdminAccount toAdminAccount (Admin admin) {
        AdminAccount adminAccount = new AdminAccount();
        adminAccount.setId(admin.getId());
        adminAccount.setPassword(admin.getPassword());
        adminAccount.setUsername(admin.getUserName());
        adminAccount.setStatus(adminAccount.getStatus());
        return adminAccount;
    }
}
