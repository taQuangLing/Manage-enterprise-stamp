package com.icheck.backend.response.stamp_response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class StampResponses {
    private List<StampResponse> stamps;
    public StampResponses(){
        stamps = new ArrayList<>();
    }
}
