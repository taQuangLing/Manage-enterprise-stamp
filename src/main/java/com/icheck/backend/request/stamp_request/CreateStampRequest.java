package com.icheck.backend.request.stamp_request;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class CreateStampRequest {
    private List<Long> listId;
    public CreateStampRequest(){
        listId = new ArrayList<>();
    }
}
