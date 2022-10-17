package com.icheck.backend.request.stamp_request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class BlockQrRequest {
    private List<Long> listId;
    public BlockQrRequest(){
        listId = new ArrayList<>();
    }
}
