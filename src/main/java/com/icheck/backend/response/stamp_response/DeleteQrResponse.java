package com.icheck.backend.response.stamp_response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteQrResponse {
    private int numError;
    private int numSuccess;
}

