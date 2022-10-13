package com.icheck.backend.request.user_package_request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AddUserPackageRequest {
    private Long userId;
    private Long packId;
}
