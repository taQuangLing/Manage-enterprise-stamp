package com.icheck.backend.response.stamp_response;

import com.icheck.backend.entity.Stamp;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StampResponse {
    private Long id;

    private String code;

    private Long userPackageId;


    private LocalDateTime createdAt;

    private int status;
    //0. pending, 1 = active, 2. block, 3. non-block

    private int deleted;
}
